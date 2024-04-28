package woowacourse.movie.feature.reservation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import woowacourse.movie.domain.reservation.Quantity
import woowacourse.movie.feature.main.ui.ScreeningModel
import woowacourse.movie.feature.reservation.ui.DailyScheduleModel
import woowacourse.movie.feature.reservation.ui.ScreeningScheduleModel
import woowacourse.movie.feature.seat.SeatSelectionActivity

class ReservationActivity : AppCompatActivity(), ReservationContract.View {
    private val presenter = ReservationPresenter(this)
    private val quantityTv by lazy {
        findViewById<TextView>(R.id.reservation_quantity)
    }

    private val posterIv by lazy {
        findViewById<ImageView>(R.id.reservation_poster)
    }

    private val movieTitleTv by lazy {
        findViewById<TextView>(R.id.reservation_movie_title)
    }

    private val movieContentTv by lazy {
        findViewById<TextView>(R.id.reservation_content)
    }

    private val openingDayTv by lazy {
        findViewById<TextView>(R.id.reservation_opening_day)
    }

    private val runningTimeTv by lazy {
        findViewById<TextView>(R.id.reservation_running_time)
    }

    private val minusBtn by lazy {
        findViewById<Button>(R.id.btn_minus)
    }

    private val plusBtn by lazy {
        findViewById<Button>(R.id.btn_plus)
    }

    private val completeBtn by lazy {
        findViewById<Button>(R.id.btn_select_seat)
    }

    private val dateSpinner by lazy {
        findViewById<Spinner>(R.id.spinner_date)
    }

    private val timeSpinner by lazy {
        findViewById<Spinner>(R.id.spinner_time)
    }

    private var timeSpinnerPosition: Int = 0
    private var dateSpinnerPosition: Int = 0
    private val quantity = Quantity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val screeningId = intent.getLongExtra(SCREENING_ID, -1L)
        presenter.fetchScreeningDetails(screeningId)
        setupReservationCompleteControls()
        setupTicketQuantityControls()
    }

    override fun initializeMovieDetails(movie: ScreeningModel) {
        val openingDayText = movie.getFormattedScreeningPeriod(this)
        val runningTimeText = movie.getFormattedRunningTime(this)
        posterIv.setImageResource(movie.poster)
        movieTitleTv.text = movie.title
        movieContentTv.text = movie.content
        openingDayTv.text = openingDayText
        runningTimeTv.text = runningTimeText
        updateTicketQuantity(quantity.value)
    }

    override fun setupScreeningSchedulesControls(screeningScheduleModel: ScreeningScheduleModel) {
        val dailySchedules = screeningScheduleModel.dailySchedules
        val dateSpinnerAdapter =
            buildArrayAdapter(
                screeningScheduleModel.getScheduleDates().toList(),
            )
        val timeSpinnerAdapter =
            buildArrayAdapter(
                dailySchedules.first().times.toList(),
            )
        dateSpinner.adapter = dateSpinnerAdapter
        timeSpinner.adapter = timeSpinnerAdapter
        setUpDateSpinnerSelection(dailySchedules, timeSpinnerAdapter)
        setUpTimeSpinnerSelection()
    }

    private fun buildArrayAdapter(items: List<String>): ArrayAdapter<String> {
        return ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            items,
        )
    }

    private fun setUpDateSpinnerSelection(
        dailySchedules: List<DailyScheduleModel>,
        timeSpinnerAdapter: ArrayAdapter<String>,
    ) {
        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    dateSpinnerPosition = position
                    updateTimeSpinnerItems(timeSpinnerAdapter, dailySchedules)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Log.d("테스트", "dateSpinner")
                }
            }
    }

    private fun setUpTimeSpinnerSelection() {
        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    timeSpinnerPosition = position
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Log.d("테스트", "timeSpinner")
                }
            }
    }

    private fun updateTimeSpinnerItems(
        timeSpinnerAdapter: ArrayAdapter<String>,
        dailySchedules: List<DailyScheduleModel>,
    ) {
        timeSpinnerAdapter.clear()
        timeSpinnerAdapter.addAll(dailySchedules[dateSpinnerPosition].times)
    }

    override fun setupReservationCompleteControls() {
        completeBtn.setOnClickListener {
            if (0 < quantity.value) navigateToCompleteScreen()
        }
    }

    override fun navigateToCompleteScreen() {
        val id = intent.getLongExtra(SCREENING_ID, -1)
        startActivity(
            SeatSelectionActivity.getIntent(
                this,
                id,
                dateSpinnerPosition,
                timeSpinnerPosition,
                quantity.value,
            ),
        )
    }

    override fun setupTicketQuantityControls() {
        minusBtn.setOnClickListener {
            quantity.decrease()
            updateTicketQuantity(quantity.value)
        }
        plusBtn.setOnClickListener {
            quantity.increase()
            updateTicketQuantity(quantity.value)
        }
    }

    override fun updateTicketQuantity(newQuantity: Int) {
        quantityTv.text = newQuantity.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val SCREENING_ID = "screening_id"

        fun getIntent(
            context: Context,
            id: Long,
        ): Intent {
            return Intent(context, ReservationActivity::class.java).apply {
                putExtra(SCREENING_ID, id)
            }
        }
    }
}
