package woowacourse.movie.moviereservation

import android.content.Context
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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.DummyMovies
import woowacourse.movie.moviereservation.uimodel.BookingInfoUiModel
import woowacourse.movie.moviereservation.uimodel.HeadCountUiModel
import woowacourse.movie.moviereservation.uimodel.MovieReservationUiModel
import woowacourse.movie.moviereservation.uimodel.ScreeningDateTimesUiModel
import woowacourse.movie.reservationresult.ReservationResultActivity
import woowacourse.movie.selectseat.SelectSeatActivity
import woowacourse.movie.util.bundleParcelable

class MovieReservationActivity : AppCompatActivity(), MovieReservationContract.View {
    private lateinit var presenter: MovieReservationPresenter
    private lateinit var countView: TextView
    private lateinit var plusButton: Button
    private lateinit var minusButton: Button
    private lateinit var dateSpinner: Spinner
    private lateinit var dateAdapter: ArrayAdapter<String>
    private lateinit var timeSpinner: Spinner
    private lateinit var timeAdapter: ArrayAdapter<String>

    private lateinit var bookingInfoUiModel: BookingInfoUiModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)

        val id = intent.getLongExtra(EXTRA_SCREEN_MOVIE_ID, INVALID_SCREEN_MOVIE_ID)
        initView()
        initClickListener()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter =
            MovieReservationPresenter(
                this, DummyMovies,
            )

        showInitView(id)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val bookingInfo = bookingInfoUiModel
        outState.putParcelable(STATE_BOOKING_ID, bookingInfo)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val storedBookingInfo =
            savedInstanceState.bundleParcelable(STATE_BOOKING_ID, BookingInfoUiModel::class.java)
        storedBookingInfo?.let {
            bookingInfoUiModel = it
            countView.text = bookingInfoUiModel.count.count
            dateSpinner.setSelection(bookingInfoUiModel.date.position)
            timeSpinner.setSelection(bookingInfoUiModel.time.position)
        }
    }

    private fun initView() {
        countView = findViewById(R.id.tv_detail_count)
        plusButton = findViewById(R.id.btn_detail_plus)
        minusButton = findViewById(R.id.btn_detail_minus)
        dateSpinner = findViewById(R.id.spinner_detail_date)
        timeSpinner = findViewById(R.id.spinner_detail_time)
    }

    private fun initClickListener() {
        plusButton.setOnClickListener {
            presenter.plusCount(bookingInfoUiModel.count)
        }
        minusButton.setOnClickListener {
            presenter.minusCount(bookingInfoUiModel.count)
        }
        findViewById<Button>(R.id.btn_detail_complete).setOnClickListener {
            startActivity(SelectSeatActivity.getIntent(this, bookingInfoUiModel))
        }
    }

    private fun showInitView(id: Long) {
        presenter.loadMovieDetail(id)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showMovieInfo(reservation: MovieReservationUiModel) {
        val (id, title, imageRes, screenDate, description, runningTime) = reservation
        findViewById<ImageView>(R.id.iv_detail_poster).setImageResource(imageRes)
        findViewById<TextView>(R.id.tv_detail_title).text = title
        findViewById<TextView>(R.id.tv_detail_movie_desc).text = description
        findViewById<TextView>(R.id.tv_detail_running_date).text = screenDate
        findViewById<TextView>(R.id.tv_detail_running_time).text = runningTime
    }

    override fun updateHeadCount(updatedCount: HeadCountUiModel) {
        bookingInfoUiModel = bookingInfoUiModel.updateCount(updatedCount)
        countView.text = bookingInfoUiModel.count.count
    }

    override fun navigateToReservationResultView(reservationId: Long) {
        startActivity(ReservationResultActivity.getIntent(this, reservationId))
    }

    override fun showScreeningMovieError() {
        Toast.makeText(this, "영화 정보를 불러오는데 실패했습니다. 앱을 다시 실행해주세요.", Toast.LENGTH_SHORT).show()
    }

    override fun showMovieReservationError() {
        Toast.makeText(this, "영화 예매에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
    }

    override fun showCantDecreaseError(minCount: Int) {
        Toast.makeText(this, "$minCount 명 이상부터 예약할 수 있습니다.", Toast.LENGTH_SHORT).show()
    }

    override fun showDefaultBookingInfo(
        screeningDateTimesUiModel: ScreeningDateTimesUiModel,
        bookingInfoUiModel: BookingInfoUiModel,
    ) {
        this.bookingInfoUiModel = bookingInfoUiModel
        initSpinner(screeningDateTimesUiModel)
        countView.text = bookingInfoUiModel.count.count
    }

    private fun initSpinner(screeningDateTimesUiModel: ScreeningDateTimesUiModel) {
        dateAdapter =
            ArrayAdapter(
                this,
                R.layout.item_spinner,
                screeningDateTimesUiModel.dates(),
            )

        timeAdapter =
            ArrayAdapter(this, R.layout.item_spinner, screeningDateTimesUiModel.defaultTimes())

        dateSpinner.adapter = dateAdapter
        timeSpinner.adapter = timeAdapter

        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    bookingInfoUiModel =
                        bookingInfoUiModel.updateDate(
                            position, dateSpinner.selectedItem.toString(),
                        )
                    timeAdapter.clear()
                    timeAdapter.addAll(screeningDateTimesUiModel.screeningTimeOfDate(position))
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }

        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    bookingInfoUiModel =
                        bookingInfoUiModel.updateTime(
                            position, timeSpinner.selectedItem.toString(),
                        )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    companion object {
        const val EXTRA_SCREEN_MOVIE_ID: String = "screenMovieId"
        const val INVALID_SCREEN_MOVIE_ID = -1L
        private const val STATE_BOOKING_ID = "booking"

        fun getIntent(
            context: Context,
            reservationId: Long,
        ): Intent {
            return Intent(context, MovieReservationActivity::class.java).apply {
                putExtra(EXTRA_SCREEN_MOVIE_ID, reservationId)
            }
        }
    }
}
