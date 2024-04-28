package woowacourse.movie.presentation.reservation.booking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import woowacourse.movie.R
import woowacourse.movie.common.ui.parcelable
import woowacourse.movie.data.MovieRepositoryFactory
import woowacourse.movie.presentation.reservation.booking.model.MovieReservationUiState
import woowacourse.movie.presentation.reservation.booking.model.ScreeningMovieUiModel
import woowacourse.movie.presentation.reservation.booking.model.SeatSelectionNavArgs
import woowacourse.movie.presentation.reservation.seat.SeatSelectionActivity

class MovieReservationActivity : AppCompatActivity(), MovieReservationView {
    private lateinit var presenter: MovieReservationPresenter
    private lateinit var countView: TextView
    private lateinit var plusButton: Button
    private lateinit var minusButton: Button
    private lateinit var dateSpinner: Spinner
    private lateinit var timeSpinner: Spinner
    private lateinit var timeSpinnerAdapter: ArrayAdapter<String>
    private lateinit var dateSpinnerAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_movie)

        initView()
        initClickListener()
        if (savedInstanceState == null) initPresenter()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_RESERVATION_UI_STATE, presenter.uiState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.parcelable<MovieReservationUiState>(
            KEY_RESERVATION_UI_STATE
        )?.let { uiState ->
            presenter =
                MovieReservationPresenter(
                    this,
                    MovieRepositoryFactory.movieRepository(),
                ).apply { restoreState(uiState) }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showMovieReservation(reservation: ScreeningMovieUiModel) {
        val (title, imageRes, screenDate, description, runningTime) = reservation
        findViewById<ImageView>(R.id.iv_reservation_poster).setImageResource(imageRes)
        findViewById<TextView>(R.id.tv_reservation_title).text = title
        findViewById<TextView>(R.id.tv_reservation_movie_description).text = description
        findViewById<TextView>(R.id.tv_reservation_running_date).text = screenDate
        findViewById<TextView>(R.id.tv_reservation_running_time).text = runningTime
    }

    override fun showErrorView() {
        val errorLayout = findViewById<LinearLayout>(R.id.cl_reservation_movie_error)
        val successLayout = findViewById<ConstraintLayout>(R.id.cl_reservation_movie_success)
        errorLayout.visibility = View.VISIBLE
        successLayout.visibility = View.GONE
    }

    override fun showHeadCount(count: Int) {
        countView.text = count.toString()
    }

    override fun showTimePicker(times: List<String>) {
        timeSpinnerAdapter.clear()
        timeSpinnerAdapter.addAll(times)
    }

    override fun showTimePickerAt(position: Int) {
        timeSpinner.postDelayed({
            timeSpinner.setSelection(position, false)
        }, 100)
    }

    override fun showScreenDateAt(position: Int) {
        dateSpinner.postDelayed({
            dateSpinner.setSelection(position, false)
        }, 100)
    }

    override fun showDatePicker(dates: List<String>) {
        dateSpinnerAdapter.clear()
        dateSpinnerAdapter.addAll(dates)
    }

    override fun navigateToSeatSelection(navArgs: SeatSelectionNavArgs) {
        val intent = SeatSelectionActivity.newIntent(this, navArgs)
        startActivity(intent)
    }

    private fun initView() {
        countView = findViewById(R.id.tv_reservation_count)
        plusButton = findViewById(R.id.btn_reservation_plus)
        minusButton = findViewById(R.id.btn_reservation_minus)
        dateSpinnerAdapter =
            ArrayAdapter(
                this@MovieReservationActivity,
                android.R.layout.simple_spinner_item,
                mutableListOf<String>(),
            )
        dateSpinner =
            findViewById<Spinner>(R.id.sp_reservation_date).apply {
                adapter = dateSpinnerAdapter
            }
        timeSpinnerAdapter =
            ArrayAdapter(
                this@MovieReservationActivity,
                android.R.layout.simple_spinner_item,
                mutableListOf<String>(),
            )
        timeSpinner =
            findViewById<Spinner>(R.id.sp_reservation_time).apply {
                adapter = timeSpinnerAdapter
            }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initClickListener() {
        plusButton.setOnClickListener {
            presenter.plusCount()
        }
        minusButton.setOnClickListener {
            presenter.minusCount()
        }
        findViewById<Button>(R.id.btn_navigate_seat_selection).setOnClickListener {
            presenter.completeReservation()
        }
        dateSpinner.onItemSelectedListener = itemSelectListener { presenter.updateScreenDateAt(it) }
        timeSpinner.onItemSelectedListener = itemSelectListener { presenter.updateScreenTimeAt(it) }
    }

    private inline fun itemSelectListener(crossinline onSelected: (Int) -> Unit): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                onSelected(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun initPresenter() {
        val id = intent.getLongExtra(KEY_SCREEN_MOVIE_ID, INVALID_ID)
        presenter =
            MovieReservationPresenter(
                this,
                MovieRepositoryFactory.movieRepository(),
            ).apply { loadScreenMovie(id) }
    }

    companion object {
        const val INVALID_ID: Long = -1
        val KEY_SCREEN_MOVIE_ID: String? = this::class.java.canonicalName
        const val KEY_RESERVATION_UI_STATE: String = "KEY_RESERVATION_UI_STATE"

        @JvmStatic
        fun newIntent(
            context: Context,
            movieId: Long,
        ): Intent =
            Intent(context, MovieReservationActivity::class.java).apply {
                putExtra(KEY_SCREEN_MOVIE_ID, movieId)
            }
    }
}
