package woowacourse.movie.presentation.reservation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.DateMaker
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieDateTime
import woowacourse.movie.domain.model.TicketCounter
import woowacourse.movie.presentation.model.MovieDateModel
import woowacourse.movie.presentation.model.PendingMovieReservationModel
import woowacourse.movie.presentation.model.toMovieDateModel
import woowacourse.movie.presentation.reservation.MovieReservationPresenter.Companion.KEY_MOVIE_DATE
import woowacourse.movie.presentation.reservation.MovieReservationPresenter.Companion.KEY_TICKET_COUNT
import woowacourse.movie.presentation.screen.MovieScreenPresenter
import woowacourse.movie.presentation.seat.SeatSelectionActivity
import woowacourse.movie.presentation.utils.toCustomString
import woowacourse.movie.presentation.utils.toDrawableIdByName
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

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
    private lateinit var dateSpinner: Spinner
    private lateinit var timeSpinner: Spinner

    private val presenter: MovieReservationPresenter by lazy {
        MovieReservationPresenter(
            view = this@MovieReservationActivity,
            movieId = loadMovieId(),
            movieRepository = MovieRepositoryImpl(),
            dateMaker = DateMaker(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)
        initView()
        presenter.loadMovie()
        loadSavedData(savedInstanceState)
        showCurrentResultTicketCountView()
        setClickListener()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.saveInstance(outState)
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
        dateSpinner = findViewById(R.id.date_spinner)
        timeSpinner = findViewById(R.id.time_spinner)
    }

    private fun setClickListener() {
        minusNumberButton.setOnClickListener {
            presenter.decreaseTicketCount()
        }
        plusNumberButton.setOnClickListener {
            presenter.increaseTicketCount()
        }
        ticketingButton.setOnClickListener {
            presenter.reservation(
                title = titleView.text.toString(),
                count = presenter.getTicketCount(),
            )
        }
    }

    override fun showMovie(movie: Movie) {
        titleView.text = movie.title
        screeningDateView.text =
            "${movie.screeningStartDate.toCustomString()} ~ ${movie.screeningEndDate.toCustomString()}"
        runningDateView.text = movie.runningTime.toString()
        descriptionView.text = movie.description
        val imageResource = movie.imageName.toDrawableIdByName(this@MovieReservationActivity)
        imageResource?.let { posterView.setImageResource(it) }
        presenter.loadDate(movie.screeningStartDate, movie.screeningEndDate)
        presenter.loadTime(movie.screeningStartDate)
    }

    override fun showCurrentResultTicketCountView() {
        ticketCountView.text = presenter.getTicketCount().toString()
    }

    override fun showDate(dates: List<LocalDate>) {
        dateSpinner.adapter =
            ArrayAdapter(
                this@MovieReservationActivity,
                android.R.layout.simple_spinner_item,
                dates.map { it.toCustomString() },
            )
        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    presenter.selectDate(dates[position])
                    presenter.loadTime(dates[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    override fun showTime(times: List<LocalTime>) {
        timeSpinner.adapter =
            ArrayAdapter(
                this@MovieReservationActivity,
                android.R.layout.simple_spinner_item,
                times.map { it.toCustomString() },
            )
        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    presenter.selectTime(times[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    override fun moveToSeatSelection(pendingMovieReservation: PendingMovieReservationModel) {
        val intent = Intent(this@MovieReservationActivity, SeatSelectionActivity::class.java)
        intent.putExtra(
            MovieReservationPresenter.KEY_NAME_PENDING_RESERVATION,
            pendingMovieReservation as Serializable,
        )
        this@MovieReservationActivity.startActivity(intent)
    }

    private fun loadSavedData(savedInstanceState: Bundle?) {
        val savedCount =
            savedInstanceState?.getInt(KEY_TICKET_COUNT) ?: TicketCounter.MIN_TICKET_COUNT
        val defaultMovieDateTimeModel = MovieDateTime().toMovieDateModel()
        val savedDate =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                savedInstanceState?.getSerializable(KEY_MOVIE_DATE, MovieDateModel::class.java)
                    ?: defaultMovieDateTimeModel
            } else {
                savedInstanceState?.getSerializable(KEY_MOVIE_DATE) as? MovieDateModel
                    ?: defaultMovieDateTimeModel
            }
        presenter.initSavedInstance(savedCount, savedDate)
    }
}
