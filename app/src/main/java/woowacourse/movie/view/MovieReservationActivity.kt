package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.contract.MovieReservationContract
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
        presenter.setMovieInfo()
        presenter.setCurrentResultTicketCountInfo()
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

    override fun setMovieView() {
        getSerializableMovieData(intent)?.let { movie ->
            val formattedScreeningDate =
                movie.screeningDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))

            titleView.text = movie.title
            screeningDateView.text = formattedScreeningDate
            runningDateView.text = movie.runningTime.toString()
            descriptionView.text = movie.description
            posterView.setImageResource(movie.posterResourceId)
        }
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
        intent.putExtra("count", info)
        this.startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val count = findViewById<TextView>(R.id.ticket_count).text.toString().toInt()
        outState.putInt("count", count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let {
            val count = it.getInt("count")
            val countTextView = findViewById<TextView>(R.id.ticket_count)
            countTextView.text = count.toString()
        }
    }
}
