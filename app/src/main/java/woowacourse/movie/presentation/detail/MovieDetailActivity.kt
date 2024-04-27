package woowacourse.movie.presentation.detail

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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import coil.load
import woowacourse.movie.R
import woowacourse.movie.presentation.seat.MovieSeatActivity
import woowacourse.movie.utils.MovieErrorCode
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_ID
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_RESERVATION_COUNT
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_SCREEN_DATE_TIME_ID
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_PARSED_MOVIE_RESERVATION_DATE
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_PARSED_MOVIE_RESERVATION_TIME
import woowacourse.movie.utils.MovieIntentConstants.NOT_FOUND_MOVIE_ID
import woowacourse.movie.utils.formatScreeningPeriod
import java.time.LocalDate
import java.time.LocalTime

class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {
    private lateinit var movieSeatActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var movieDetailPresenter: MovieDetailPresenter

    private val detailImage: ImageView by lazy { findViewById(R.id.detailImage) }
    private val detailTitle: TextView by lazy { findViewById(R.id.detailTitle) }
    private val detailDate: TextView by lazy { findViewById(R.id.detailDate) }
    private val minusButton: Button by lazy { findViewById(R.id.detailMinusBtn) }
    private val plusButton: Button by lazy { findViewById(R.id.detailPlusBtn) }
    private val detailDateSpinner: Spinner by lazy { findViewById(R.id.detailDateSpinner) }
    private val detailTimeSpinner: Spinner by lazy { findViewById(R.id.detailTimeSpinner) }
    private val detailRunningTime: TextView by lazy { findViewById(R.id.detailRunningTime) }
    private val detailDescription: TextView by lazy { findViewById(R.id.detailDescription) }
    private val reservationCount: TextView by lazy { findViewById(R.id.detailReservCount) }
    private val reservationCompleteButton: Button by lazy { findViewById(R.id.detailReservCompleteBtn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movieSeatActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == MovieErrorCode.INVALID_MOVIE_ID.code) {
                    Toast.makeText(this, MovieErrorCode.INVALID_MOVIE_ID.msg, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        movieDetailPresenter = MovieDetailPresenter(this)
        movieDetailPresenter.loadMovie(intent.getLongExtra(EXTRA_MOVIE_ID, NOT_FOUND_MOVIE_ID))
        initClickListeners()
    }

    private fun initClickListeners() {
        minusButton.setOnClickListener {
            movieDetailPresenter.minusReservationCount()
        }
        plusButton.setOnClickListener {
            movieDetailPresenter.plusReservationCount()
        }
        reservationCompleteButton.setOnClickListener {
            movieDetailPresenter.selectSeat(
                detailDateSpinner.selectedItem as LocalDate,
                detailTimeSpinner.selectedItem as LocalTime,
            )
        }
        detailDateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedLocalDate = parent?.getItemAtPosition(position) as LocalDate
                    movieDetailPresenter.selectDate(selectedLocalDate, position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) { }
            }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        with(savedInstanceState) {
            reservationCount.text = this.getInt(EXTRA_MOVIE_RESERVATION_COUNT).toString()
            movieDetailPresenter.restoreDateTime(
                this.getSerializable(EXTRA_PARSED_MOVIE_RESERVATION_DATE) as LocalDate,
                this.getSerializable(
                    EXTRA_PARSED_MOVIE_RESERVATION_TIME,
                ) as LocalTime,
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(EXTRA_MOVIE_RESERVATION_COUNT, reservationCount.text.toString().toInt())
        outState.putSerializable(EXTRA_PARSED_MOVIE_RESERVATION_DATE, detailDateSpinner.selectedItem as LocalDate)
        outState.putSerializable(EXTRA_PARSED_MOVIE_RESERVATION_TIME, detailTimeSpinner.selectedItem as LocalTime)
    }

    override fun onUpdateView(detailUiModel: DetailUiModel) {
        detailUiModel.movie?.let {
            detailImage.load(it.thumbnailUrl)
            detailTitle.text = it.title
            detailDate.text = formatScreeningPeriod(it.screenDateTime.map { it.dateTime })
            detailRunningTime.text = "${it.runningTime}"
            detailDescription.text = it.description
        }
        reservationCount.text = detailUiModel.reservationCount.count.toString()
    }

    override fun updateDateList(
        dates: List<LocalDate>?,
        selectedPosition: Int?,
    ) {
        dates?.let {
            detailDateSpinner.adapter =
                ArrayAdapter(
                    this@MovieDetailActivity,
                    android.R.layout.simple_spinner_item,
                    it,
                )
        }
        selectedPosition?.let {
            detailDateSpinner.setSelection(it)
        }
    }

    override fun updateTimeList(
        times: List<LocalTime>?,
        selectedPosition: Int?,
    ) {
        times?.let {
            detailTimeSpinner.adapter =
                ArrayAdapter(
                    this@MovieDetailActivity,
                    android.R.layout.simple_spinner_item,
                    it,
                )
        }
        selectedPosition?.let {
            detailTimeSpinner.setSelection(it)
        }
    }

    override fun onError(errorCode: MovieErrorCode) {
        // 에러 발생 시에, 이전 액티비티로 이동하며 메세지 전달
        setResult(errorCode.code)
        finish()
    }

    override fun onSelectSeatClicked(
        movieId: Long?,
        movieScreenDateTimeId: Long?,
        count: Int,
    ) {
        Intent(this, MovieSeatActivity::class.java).apply {
            putExtra(EXTRA_MOVIE_ID, movieId)
            putExtra(EXTRA_MOVIE_SCREEN_DATE_TIME_ID, movieScreenDateTimeId)
            putExtra(EXTRA_MOVIE_RESERVATION_COUNT, count)
            movieSeatActivityResultLauncher.launch(this)
        }
    }
}
