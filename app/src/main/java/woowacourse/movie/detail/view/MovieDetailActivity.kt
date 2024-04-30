package woowacourse.movie.detail.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.detail.presenter.MovieDetailPresenter
import woowacourse.movie.detail.presenter.contract.MovieDetailContract
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieCount
import woowacourse.movie.model.MovieDate
import woowacourse.movie.model.MovieTime
import woowacourse.movie.seatselection.view.MovieSeatSelectionActivity
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_ID
import woowacourse.movie.util.MovieIntentConstant.KEY_ITEM_POSITION
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_COUNT
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_DATE
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_ID
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_TIME
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {
    private lateinit var detailImage: ImageView
    private lateinit var detailTitle: TextView
    private lateinit var startDate: TextView
    private lateinit var endDate: TextView
    private lateinit var detailRunningTime: TextView
    private lateinit var detailDescription: TextView
    private lateinit var reservationCount: TextView
    private lateinit var minusButton: Button
    private lateinit var plusButton: Button
    private lateinit var seatSelectionButton: Button
    private lateinit var dateSpinner: Spinner
    private lateinit var runningTimeSpinner: Spinner

    private lateinit var movieDetailPresenter: MovieDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setUpViewById()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpButtonAction()

        movieDetailPresenter =
            MovieDetailPresenter(this)
        movieDetailPresenter.loadMovieDetail(
            intent.getLongExtra(
                KEY_MOVIE_ID,
                INVALID_VALUE_MOVIE_ID,
            ),
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val position = runningTimeSpinner.selectedItemPosition
        outState.putInt(KEY_ITEM_POSITION, position)

        val count = reservationCount.text.toString().toInt()
        outState.putInt(KEY_MOVIE_COUNT, count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val savedPosition = savedInstanceState.getInt(KEY_ITEM_POSITION)
        movieDetailPresenter.updateTimeSpinnerPosition(savedPosition)

        val savedCount = savedInstanceState.getInt(KEY_MOVIE_COUNT)
        movieDetailPresenter.updateRevervationCount(savedCount)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun displayMovieDetail(
        movieData: Movie?,
        movieCount: MovieCount,
    ) {
        movieData?.let { movie ->
            detailImage.setImageResource(movie.thumbnail)
            detailTitle.text = movie.title
            startDate.text = movie.date.startLocalDate.toString()
            endDate.text = movie.date.endLocalDate.toString()
            detailRunningTime.text = movie.runningTime.toString()
            detailDescription.text = movie.description
            reservationCount.text = movieCount.count.toString()

            seatSelectionButton.setOnClickListener {
                movieDetailPresenter.reserveMovie(
                    movie.id,
                    dateSpinner.selectedItem.toString(),
                    runningTimeSpinner.selectedItem.toString(),
                )
            }
        }
    }

    override fun updateCountView(count: Int) {
        reservationCount.text = count.toString()
    }

    override fun setUpDateSpinner(movieDate: MovieDate) {
        dateSpinner.adapter =
            ArrayAdapter(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                movieDate.generateDates(),
            )

        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedDate =
                        parent?.getItemAtPosition(position).toString()
                    val localDate = LocalDate.parse(selectedDate)
                    movieDetailPresenter.loadTimeSpinnerItem(localDate)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    override fun setUpTimeSpinner(
        movieTime: MovieTime,
        position: Int,
    ) {
        val times =
            movieTime.generateTimes().map { time ->
                time.format(DateTimeFormatter.ofPattern("kk:mm"))
            }

        runningTimeSpinner.adapter =
            ArrayAdapter(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                times,
            )
        runningTimeSpinner.setSelection(position)
    }

    override fun navigateToSeatSelectionView(
        id: Long,
        date: String,
        time: String,
        count: Int,
    ) {
        Intent(this, MovieSeatSelectionActivity::class.java).apply {
            putExtra(KEY_MOVIE_ID, id)
            putExtra(KEY_MOVIE_DATE, date)
            putExtra(KEY_MOVIE_TIME, time)
            putExtra(KEY_MOVIE_COUNT, count)
            startActivity(this)
        }
    }

    private fun setUpButtonAction() {
        minusButton.setOnClickListener {
            movieDetailPresenter.minusReservationCount()
        }
        plusButton.setOnClickListener {
            movieDetailPresenter.plusReservationCount()
        }
    }

    private fun setUpViewById() {
        detailImage = findViewById(R.id.detailImage)
        detailTitle = findViewById(R.id.detailTitle)
        startDate = findViewById(R.id.startDate)
        endDate = findViewById(R.id.endDate)
        detailRunningTime = findViewById(R.id.detailRunningTime)
        detailDescription = findViewById(R.id.detailDescription)
        reservationCount = findViewById(R.id.detailReservCount)
        minusButton = findViewById(R.id.detailMinusBtn)
        plusButton = findViewById(R.id.detailPlusBtn)
        seatSelectionButton = findViewById(R.id.seatSelectionBtn)
        dateSpinner = findViewById(R.id.dateSpinner)
        runningTimeSpinner = findViewById(R.id.runningTimeSpinner)
    }
}
