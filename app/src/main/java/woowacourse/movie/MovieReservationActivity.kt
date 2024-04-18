package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class MovieReservationActivity : AppCompatActivity(), MovieReservationContract.View {
    private lateinit var context: Context
    private lateinit var titleView: TextView
    private lateinit var screeningDateView: TextView
    private lateinit var runningDateView: TextView
    private lateinit var ticketCountView: TextView
    private lateinit var posterView: ImageView
    private lateinit var descriptionView: TextView
    private lateinit var minusNumberButton: Button
    private lateinit var plusNumberButton: Button
    private lateinit var ticketingButton: Button
    private val presenter = MovieReservationPresenter(this@MovieReservationActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)
        context = this@MovieReservationActivity

        initView()
        setMovieData()
        showCurrentResultTicketCountView()
        setClickListener()
    }

    private fun getSerializableModel(): Movie? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(Movie.KEY_NAME_MOVIE, Movie::class.java)
        } else {
            intent.getSerializableExtra(Movie.KEY_NAME_MOVIE) as? Movie
        }
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

    private fun setMovieData() {
        getSerializableModel()?.let { movie ->
            titleView.text = movie.title
            screeningDateView.text = movie.screeningDate
            runningDateView.text = movie.runningTime.toString()
            descriptionView.text = movie.description
            posterView.setImageResource(movie.posterResourceId)
        }
    }

    private fun setClickListener() {
        minusNumberButton.setOnClickListener {
            presenter.clickMinusNumberButton()
        }
        plusNumberButton.setOnClickListener {
            presenter.clickPlusNumberButton()
        }
        ticketingButton.setOnClickListener {
            ticketing()
        }
    }

    private fun ticketing() {
        val ticket = makeTicket()

        val intent = Intent(context, MovieReservationCompleteActivity::class.java)
        intent.putExtra(Ticket.KEY_NAME_TICKET, ticket as Serializable)
        context.startActivity(intent)
    }

    private fun makeTicket() =
        Ticket(
            title = titleView.text.toString(),
            screeningDate = screeningDateView.text.toString(),
            count = presenter.ticketCount,
        )

    override fun showCurrentResultTicketCountView() {
        ticketCountView.text = presenter.ticketCount.toString()
    }
}
