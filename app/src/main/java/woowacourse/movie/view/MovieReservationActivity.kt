package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.contract.MovieReservationContract
import woowacourse.movie.model.Movie
import woowacourse.movie.presenter.MovieReservationPresenter
import woowacourse.movie.util.IntentUtil.getSerializableMovieData
import java.time.format.DateTimeFormatter

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
    override val presenter = MovieReservationPresenter(this@MovieReservationActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)
        initView()
        presenter.setCurrentResultTicketCountInfo()
        presenter.storeMovieData(getSerializableMovieData(intent))
        presenter.setMovieInfo()
        setOnPlusButtonClickListener()
        setOnMinusButtonClickListener()
        setOnTicketingButtonListener()
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

    override fun setMovieView(info: Movie) {
        presenter.storeMovieData(getSerializableMovieData(intent))
        val formattedScreeningDate =
            info.screeningDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN))

        titleView.text = info.title
        screeningDateView.text = formattedScreeningDate
        runningDateView.text = info.runningTime.toString()
        descriptionView.text = info.description
        posterView.setImageResource(info.posterResourceId)
    }

    override fun showCurrentResultTicketCountView(info: Int) {
        ticketCountView.text = info.toString()
    }

    override fun setOnPlusButtonClickListener() {
        plusNumberButton.setOnClickListener {
            presenter.setPlusButtonClickInfo()
        }
    }

    override fun setOnMinusButtonClickListener() {
        minusNumberButton.setOnClickListener {
            presenter.setMinusButtonClickInfo()
        }
    }

    override fun setOnTicketingButtonListener() {
        ticketingButton.setOnClickListener {
            presenter.setTicketingButtonClickInfo()
        }
    }

    override fun startMovieTicketActivity(info: Int) {
        val intent = Intent(this, MovieTicketActivity::class.java)
        intent.putExtra(EXTRA_COUNT_KEY, info)
        this.startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val count = findViewById<TextView>(R.id.ticket_count).text.toString().toInt()
        outState.putInt(EXTRA_COUNT_KEY, count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let {
            val count = it.getInt(EXTRA_COUNT_KEY)
            val countTextView = findViewById<TextView>(R.id.ticket_count)
            countTextView.text = count.toString()
        }
    }

    companion object {
        const val EXTRA_COUNT_KEY = "count_key"
        private const val DATE_PATTERN = "yyyy.MM.dd"
    }
}
