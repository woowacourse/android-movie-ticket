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
import woowacourse.movie.data.MovieRepositoryFactory
import woowacourse.movie.presentation.reservation.result.ReservationResultActivity

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
        outState.apply {
            putInt(KEY_RESERVATION_COUNT, presenter.count())
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val count = savedInstanceState.getInt(KEY_RESERVATION_COUNT)
        val id = intent.getLongExtra(KEY_SCREEN_MOVIE_ID, INVALID_ID)
        presenter =
            MovieReservationPresenter(
                this,
                MovieRepositoryFactory.movieRepository(),
                count,
            ).apply { loadScreenMovie(id) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showMovieReservation(reservation: MovieReservationUiModel) {
        val (id, title, imageRes, screenDate, description, runningTime) = reservation
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

    override fun updateHeadCount(count: Int) {
        countView.text = count.toString()
    }

    override fun updateTimePicker(times: List<String>) {
        timeSpinnerAdapter.clear()
        timeSpinnerAdapter.addAll(times)
        // TODO 안해도 될지도?
        timeSpinnerAdapter.notifyDataSetChanged()
    }

    override fun updateDatePicker(dates: List<String>) {
        if (dateSpinnerAdapter.isEmpty.not()) dateSpinnerAdapter.clear()
        dateSpinnerAdapter.addAll(dates)
        // TODO 안해도 될지도?
        dateSpinnerAdapter.notifyDataSetChanged()
    }

    override fun navigateToReservationResultView(reservationId: Long) {
        val intent = ReservationResultActivity.newIntent(this, reservationId)
        startActivity(intent)
    }

    private fun initView() {
        countView = findViewById(R.id.tv_reservation_count)
        plusButton = findViewById(R.id.btn_reservation_plus)
        minusButton = findViewById(R.id.btn_reservation_minus)
        dateSpinnerAdapter = ArrayAdapter(
            this@MovieReservationActivity,
            android.R.layout.simple_spinner_item,
            mutableListOf<String>()
        )
        dateSpinner = findViewById<Spinner>(R.id.sp_reservation_date).apply {
            adapter = dateSpinnerAdapter
            onItemSelectedListener = itemSelectListener { presenter.updateScreenDateAt(it) }
        }
        timeSpinnerAdapter = ArrayAdapter(
            this@MovieReservationActivity,
            android.R.layout.simple_spinner_item,
            mutableListOf<String>()
        )
        timeSpinner = findViewById<Spinner>(R.id.sp_reservation_time).apply {
            adapter = timeSpinnerAdapter
            onItemSelectedListener = itemSelectListener { presenter.updateScreenTimeAt(it) }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun updateTimePickerAt(position: Int) {
        timeSpinner.setSelection(position)
    }

    private fun initClickListener() {
        plusButton.setOnClickListener {
            presenter.plusCount()
        }
        minusButton.setOnClickListener {
            presenter.minusCount()
        }
        findViewById<Button>(R.id.btn_reservation_complete).setOnClickListener {
            presenter.completeReservation()
        }
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
        const val KEY_RESERVATION_COUNT: String = "KEY_RESERVATION_COUNT"

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