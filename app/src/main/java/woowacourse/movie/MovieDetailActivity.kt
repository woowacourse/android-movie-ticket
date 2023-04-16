package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import woowacourse.movie.domain.MovieSchedule

class MovieDetailActivity : BackButtonActivity() {
    private var isFirst = true
    private val dateSpinner: Spinner by lazy { findViewById(R.id.sp_movie_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.sp_movie_time) }
    private val personCountTextView by lazy { findViewById<TextView>(R.id.tv_ticket_count) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        isFirst = true

        val movieData = intent.getSerializableCompat(MovieListAdapter.MOVIE_DATA_KEY)
            ?: Movie.nullData
        processMovieNullData(movieData)

        initView(movieData)

        val movieSchedule = MovieSchedule(movieData.startDate, movieData.endDate)
        val scheduleDate = movieSchedule.getScheduleDates()

        initView(movieData)
        setClickListener(movieData)
        setSpinnerSelectedListener(movieSchedule, scheduleDate, savedInstanceState)
        setSpinnerAdapter(scheduleDate, movieSchedule)
        reloadTicketCountInstance(savedInstanceState)
    }

    private fun setSpinnerAdapter(
        scheduleDate: List<String>,
        movieSchedule: MovieSchedule
    ) {
        dateSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            scheduleDate
        )
        timeSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            movieSchedule.getScheduleTimes(scheduleDate[0])
        )
    }

    private fun initView(movieData: Movie) {
        findViewById<ImageView>(R.id.iv_movie_poster).setImageResource(movieData.poster)
        findViewById<TextView>(R.id.tv_movie_title).text = movieData.title
        findViewById<TextView>(R.id.tv_movie_release_date).text = movieData.releaseDate
        findViewById<TextView>(R.id.tv_movie_running_time).text = movieData.runningTime
        findViewById<TextView>(R.id.tv_movie_synopsis).text = movieData.synopsis
    }

    private fun reloadTicketCountInstance(
        savedInstanceState: Bundle?,
    ) {
        if (savedInstanceState != null) {
            personCountTextView.text = savedInstanceState.getString("ticketCount")
        }
    }

    private fun processMovieNullData(movieData: Movie) {
        if (movieData == Movie.nullData) {
            Toast.makeText(this, getString(R.string.cant_get_movie_data), Toast.LENGTH_SHORT).show()
            this.finish()
        }
    }

    private fun setSpinnerSelectedListener(
        movieSchedule: MovieSchedule,
        scheduleDate: List<String>,
        savedInstanceState: Bundle?
    ) {
        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                timeSpinner.adapter = ArrayAdapter(
                    this@MovieDetailActivity,
                    android.R.layout.simple_spinner_item,
                    movieSchedule.getScheduleTimes(scheduleDate[position])
                )
                if (isFirst && savedInstanceState != null) {
                    timeSpinner.setSelection(
                        (
                            savedInstanceState.getString("time")
                                ?: movieSchedule.getScheduleTimes(dateSpinner.selectedItem.toString())
                                    .first()
                            ).toInt()
                    )
                    isFirst = false
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun setClickListener(movieData: Movie) {

        var currentCount = personCountTextView.text.toString().toInt()

        findViewById<Button>(R.id.bt_ticket_count_minus).setOnClickListener {
            if (personCountTextView.text == "1") {
                Toast.makeText(this, "1장 이상의 표를 선택해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            currentCount--
            personCountTextView.text = currentCount.toString()
        }

        findViewById<Button>(R.id.bt_ticket_count_plus).setOnClickListener {
            currentCount++
            personCountTextView.text = currentCount.toString()
        }

        findViewById<Button>(R.id.bt_book_complete).setOnClickListener {
            val intent = Intent(this, BookCompleteActivity::class.java)
            intent.putExtra(
                "movieBookingInfo",
                MovieBookingInfo(
                    movieData, dateSpinner.selectedItem.toString(),
                    timeSpinner.selectedItem.toString(),
                    currentCount
                )
            )
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("time", timeSpinner.selectedItemPosition.toString())
        outState.putInt("date", dateSpinner.selectedItemPosition)
        outState.putString("ticketCount", personCountTextView.text.toString())
    }
}
