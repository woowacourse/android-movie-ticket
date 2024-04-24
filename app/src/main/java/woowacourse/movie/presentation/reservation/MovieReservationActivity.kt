package woowacourse.movie.presentation.reservation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.presentation.detail.TicketDetailActivity
import woowacourse.movie.presentation.reservation.model.TicketModel
import woowacourse.movie.presentation.screen.MovieScreenPresenter
import woowacourse.movie.presentation.utils.toCustomString
import woowacourse.movie.presentation.utils.toDrawableIdByName
import woowacourse.movie.presentation.utils.toLocalDate
import java.io.Serializable

class MovieReservationActivity : AppCompatActivity(), MovieReservationContract.View {
    private lateinit var titleView: TextView
    private lateinit var screeningDateView: TextView
    private lateinit var runningDateView: TextView
    private lateinit var ticketCountView: TextView
    private lateinit var posterView: ImageView
    private lateinit var descriptionView: TextView
    private lateinit var minusNumberButton: Button
    private lateinit var plusNumberButton: Button
    private lateinit var ticketingButton: Button
    private val presenter: MovieReservationPresenter by lazy {
        MovieReservationPresenter(
            view = this@MovieReservationActivity,
            movieId = loadMovieId(),
            movieRepository = MovieRepositoryImpl(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)
        initView()
        presenter.loadMovie()
        showCurrentResultTicketCountView()
        setClickListener()
    }

    private fun loadMovieId(): Int {
        return intent.getIntExtra(MovieScreenPresenter.KEY_NAME_MOVIE, -1)
    }

    private fun initView() {
        titleView = findViewById(R.id.movie_detail_title)
        screeningDateView = findViewById(R.id.movie_detail_screening_date)
        runningDateView = findViewById(R.id.movie_detail_running_date)
        descriptionView = findViewById(R.id.movie_detail_description)
        ticketCountView = findViewById(R.id.ticket_count)
        posterView = findViewById(R.id.movie_detail_poster)
        minusNumberButton = findViewById(R.id.minus_button)
        plusNumberButton = findViewById(R.id.plus_button)
        ticketingButton = findViewById(R.id.ticketing_button)
    }

    private fun setClickListener() {
        minusNumberButton.setOnClickListener {
            presenter.decreaseTicketCount()
        }
        plusNumberButton.setOnClickListener {
            presenter.increaseTicketCount()
        }
        ticketingButton.setOnClickListener {
            requestTicketCount { ticketCount ->
                presenter.ticketing(
                    title = titleView.text.toString(),
                    screeningDate = screeningDateView.text.toString().toLocalDate(),
                    count = ticketCount,
                    price = ticketCount * Movie.DEFAULT_MOVIE_PRICE,
                )
            }
        }
    }

    override fun showMovie(movie: Movie) {
        titleView.text = movie.title
        screeningDateView.text = movie.screeningDate.toCustomString()
        runningDateView.text = movie.runningTime.toString()
        descriptionView.text = movie.description
        val imageResource = movie.imageName.toDrawableIdByName(this@MovieReservationActivity)
        imageResource?.let { posterView.setImageResource(it) }
    }

    override fun showCurrentResultTicketCountView() {
        requestTicketCount { ticketCount ->
            ticketCountView.text = ticketCount.toString()
        }
    }

    override fun moveToTicketDetail(ticketModel: TicketModel) {
        val intent = Intent(this@MovieReservationActivity, TicketDetailActivity::class.java)
        intent.putExtra(MovieReservationPresenter.KEY_NAME_TICKET, ticketModel as Serializable)
        this@MovieReservationActivity.startActivity(intent)
    }

    override fun requestTicketCount(count: (Int) -> Unit) {
        count(presenter.getTicketCount())
    }
}
