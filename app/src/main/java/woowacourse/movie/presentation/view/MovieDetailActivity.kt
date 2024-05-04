package woowacourse.movie.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.contract.MovieDetailContract
import woowacourse.movie.presentation.presenter.MovieDetailPresenterImpl
import woowacourse.movie.presentation.uimodel.MovieUiModel

class MovieDetailActivity : BaseActivity(), MovieDetailContract.View {
    private lateinit var movieDetailPresenter: MovieDetailContract.Presenter
    private lateinit var timeSpinnerAdapter: ArrayAdapter<String>
    private val dateSpinner: Spinner by lazy {
        findViewById(R.id.dateSpinner)
    }
    private val timeSpinner: Spinner by lazy {
        findViewById(R.id.timeSpinner)
    }
    private val reservationCountTextView: TextView by lazy {
        findViewById(R.id.reservationInfo)
    }
    private val reserveButton: Button by lazy {
        findViewById(R.id.reserveButton)
    }

    override fun getLayoutResId(): Int = R.layout.activity_movie_detail

    override fun onCreateSetup(savedInstanceState: Bundle?) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getIntExtra(INTENT_MOVIE_ID, DEFAULT_MOVIE_ID)

        movieDetailPresenter = MovieDetailPresenterImpl(movieId)
        movieDetailPresenter.attachView(this)

        savedInstanceState?.let {
            val count = it.getInt(SIS_COUNT_KEY)
            movieDetailPresenter.initReservationCount(count)
        }

        setupReservationCountButton()
        setReserveButton()
    }

    override fun onDestroy() {
        super.onDestroy()
        movieDetailPresenter.detachView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val count = reservationCountTextView.text.toString().toInt()
        outState.putInt(SIS_COUNT_KEY, count)
    }

    override fun showMovieDetail(movieUiModel: MovieUiModel) {
        findViewById<ImageView>(R.id.posterImage).setImageResource(movieUiModel.posterImageId)
        findViewById<TextView>(R.id.title).text = movieUiModel.title
        findViewById<TextView>(R.id.screeningDate).text =
            getString(
                R.string.screening_date_format,
                movieUiModel.screeningStartDate,
                movieUiModel.screeningEndDate,
            )
        findViewById<TextView>(R.id.runningTime).text =
            getString(R.string.running_time_format, movieUiModel.runningTime)
        findViewById<TextView>(R.id.summary).text = movieUiModel.summary
    }

    override fun setScreeningDatesAndTimes(
        dates: List<String>,
        times: List<String>,
        defaultDataIndex: Int,
    ) {
        attachDateSpinnerAdapter(dates, defaultDataIndex)
        attachTimeSpinnerAdapter(times, defaultDataIndex)
    }

    private fun attachDateSpinnerAdapter(
        dates: List<String>,
        defaultDataIndex: Int,
    ) {
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, dates).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        dateSpinner.adapter = adapter
        dateSpinner.setSelection(defaultDataIndex)
        onSelectDateListener()
    }

    override fun updateScreeningTimes(
        times: List<String>,
        defaultDataIndex: Int,
    ) {
        timeSpinnerAdapter.clear()
        timeSpinnerAdapter.addAll(times)
        timeSpinnerAdapter.notifyDataSetChanged()
        timeSpinner.setSelection(defaultDataIndex)
    }

    private fun attachTimeSpinnerAdapter(
        times: List<String>,
        defaultDataIndex: Int,
    ) {
        timeSpinnerAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, times).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        timeSpinner.adapter = timeSpinnerAdapter
        timeSpinner.setSelection(defaultDataIndex)
        onSelectTimeListener()
    }

    private fun onSelectDateListener() {
        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long,
                ) {
                    val selectedDate = parent.getItemAtPosition(position) as String
                    movieDetailPresenter.selectDate(selectedDate)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    dateSpinner.setSelection(DEFAULT_SPINNER_INDEX)
                }
            }
    }

    private fun onSelectTimeListener() {
        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long,
                ) {
                    val selectedTime = parent.getItemAtPosition(position) as String
                    movieDetailPresenter.selectTime(selectedTime)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    timeSpinner.setSelection(DEFAULT_SPINNER_INDEX)
                }
            }
    }

    private fun setupReservationCountButton() {
        findViewById<TextView>(R.id.minusButton).setOnClickListener {
            movieDetailPresenter.minusReservationCount()
        }

        findViewById<TextView>(R.id.plusButton).setOnClickListener {
            movieDetailPresenter.plusReservationCount()
        }
    }

    override fun showReservationCount(count: Int) {
        reservationCountTextView.text = count.toString()
    }

    override fun moveToSeatSelection(
        reservationCount: Int,
        title: String,
    ) {
        val intent = Intent(this, SeatSelectionActivity::class.java)
        intent.putExtra(SeatSelectionActivity.INTENT_TITLE, title)
        intent.putExtra(SeatSelectionActivity.INTENT_RESERVATION_COUNT, reservationCount)
        startActivity(intent)
    }

    private fun setReserveButton() {
        reserveButton.setOnClickListener {
            movieDetailPresenter.onReserveButtonClicked()
        }
    }

    companion object {
        const val DEFAULT_MOVIE_ID = -1
        const val INTENT_MOVIE_ID = "movieId"
        const val SIS_COUNT_KEY = "count"
        const val DEFAULT_SPINNER_INDEX = 0
    }
}
