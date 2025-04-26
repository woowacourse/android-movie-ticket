package woowacourse.movie.detailbooking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.completedbooking.CompletedBookingActivity
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Ticket
import woowacourse.movie.utils.DateFormatter
import woowacourse.movie.utils.parcelableCompat
import java.time.LocalDate
import java.time.LocalTime

class DetailBookingActivity : AppCompatActivity(), DetailBookingContract.View {
    private lateinit var detailBookingPresenter: DetailBookingPresenter

    private val decreasedButton: Button by lazy { findViewById(R.id.decrement_button) }
    private val increasedButton: Button by lazy { findViewById(R.id.increment_button) }
    private val spinnerDate: Spinner by lazy { findViewById(R.id.detail_spinner_date) }
    private val spinnerTime: Spinner by lazy { findViewById(R.id.detail_spinner_time) }
    private val reservationButton: Button by lazy { findViewById(R.id.detail_reservation_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_detail_booking_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        detailBookingPresenter = DetailBookingPresenter(this)

        val movie: Movie = intent.parcelableCompat(KEY_MOVIE, Movie::class.java)
        detailBookingPresenter.set(movie)

        decreasedButton.setOnClickListener {
            detailBookingPresenter.decreasedCount()
        }

        increasedButton.setOnClickListener {
            detailBookingPresenter.increasedCount()
        }

        reservationButton.setOnClickListener {
            detailBookingPresenter.clickedButton()
        }

        if (savedInstanceState != null) {
            detailBookingPresenter.restoreState(savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        detailBookingPresenter.saveState(outState)
    }

    override fun showMovieData(movie: Movie) {
        findViewById<TextView>(R.id.detail_movie_title).text = movie.title
        val dateFormatter = DateFormatter()
        findViewById<TextView>(R.id.detail_movie_date).text =
            getString(R.string.movieDate, dateFormatter.format(movie.date.startDate), dateFormatter.format(movie.date.endDate))
        findViewById<TextView>(R.id.detail_movie_time).text =
            getString(R.string.movieTime, movie.time.toString())
        findViewById<ImageView>(R.id.detail_movie_image).setImageResource(movie.image)
    }

    override fun showMovieSchedule(
        movieSchedule: List<LocalDate>,
        selectedIndex: Int,
    ) {
        spinnerDate.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, movieSchedule)
        spinnerDate.setSelection(selectedIndex)
        spinnerDate.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    detailBookingPresenter.clickedDate(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    override fun showMovieScreeningTime(
        screeningTime: List<LocalTime>,
        selectedIndex: Int,
    ) {
        spinnerTime.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, screeningTime).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        spinnerTime.setSelection(selectedIndex)
        spinnerTime.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    detailBookingPresenter.clickedTime(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    override fun showCount(count: Int) {
        findViewById<TextView>(R.id.detail_personnel).text = count.toString()
    }

    override fun showDialog(ticket: Ticket) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_reservation_title))
            .setMessage(getString(R.string.dialog_reservation_message))
            .setPositiveButton(getString(R.string.dialog_reservation_positive_text)) { _, _ ->
                startActivity(CompletedBookingActivity.newIntent(this, ticket))
            }
            .setNegativeButton(getString(R.string.dialog_reservation_negative_text)) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    companion object {
        private const val KEY_MOVIE = "movie"

        fun newIntent(
            context: Context,
            movie: Movie,
        ): Intent {
            return Intent(context, DetailBookingActivity::class.java).putExtra(KEY_MOVIE, movie)
        }
    }
}
