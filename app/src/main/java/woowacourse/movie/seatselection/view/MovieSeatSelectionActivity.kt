package woowacourse.movie.seatselection.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.model.MovieSeat
import woowacourse.movie.model.MovieSelectedSeats
import woowacourse.movie.result.view.MovieResultActivity
import woowacourse.movie.seatselection.presenter.MovieSeatSelectionPresenter
import woowacourse.movie.seatselection.presenter.contract.MovieSeatSelectionContract
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_COUNT
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_ID
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_COUNT
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_DATE
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_ID
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_SEATS
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_TIME
import woowacourse.movie.util.MovieIntentConstant.KEY_SELECTED_POSITIONS
import java.text.DecimalFormat

class MovieSeatSelectionActivity : AppCompatActivity(), MovieSeatSelectionContract.View {
    private lateinit var seatTitle: TextView
    private lateinit var seatPrice: TextView
    private lateinit var completeButton: Button
    private val tableSeats: List<TextView> by lazy {
        findViewById<TableLayout>(R.id.seatTable).children.filterIsInstance<TableRow>()
            .flatMap { tableRow ->
                tableRow.children.filterIsInstance<TextView>().toList()
            }.toList()
    }

    private lateinit var seatSelectionPresenter: MovieSeatSelectionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_seat_selection)
        setUpViewById()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        seatSelectionPresenter =
            MovieSeatSelectionPresenter(
                this,
                intent?.getIntExtra(KEY_MOVIE_COUNT, INVALID_VALUE_MOVIE_COUNT)
                    ?: INVALID_VALUE_MOVIE_COUNT,
            )

        seatSelectionPresenter.loadMovieTitle(
            intent.getLongExtra(
                KEY_MOVIE_ID,
                INVALID_VALUE_MOVIE_ID,
            ),
        )
        seatSelectionPresenter.loadTableSeats()

        setUpSelectedSeats(savedInstanceState?.getIntArray(KEY_SELECTED_POSITIONS))
        setUpCompleteButton()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val selectedPositions = seatSelectionPresenter.movieSelectedSeats.getSelectedPositions()
        outState.putIntArray(KEY_SELECTED_POSITIONS, selectedPositions)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun displayMovieTitle(movieTitle: String) {
        seatTitle.text = movieTitle
    }

    override fun setUpTableSeats(baseSeats: List<MovieSeat>) {
        tableSeats.forEachIndexed { index, view ->
            val seat = baseSeats[index]
            view.text = getString(R.string.seat, ('A'.code + seat.row).toChar(), seat.column + 1)
            view.setTextColor(ContextCompat.getColor(this, seat.grade.color))

            view.setOnClickListener {
                seatSelectionPresenter.clickTableSeat(index)
            }
        }
    }

    override fun updateSeatBackgroundColor(
        index: Int,
        backgroundColor: Int,
    ) {
        tableSeats[index].setBackgroundColor(ContextCompat.getColor(this, backgroundColor))
    }

    override fun displayDialog() {
        AlertDialog.Builder(this).setTitle("예매 확인").setMessage("정말 예매하시겠습니까?")
            .setPositiveButton("예매 완료") { _, _ -> seatSelectionPresenter.clickPositiveButton() }
            .setNegativeButton("취소") { dialog, _ -> dialog.dismiss() }.setCancelable(false).show()
    }

    override fun updateSelectResult(movieSelectedSeats: MovieSelectedSeats) {
        seatPrice.text = DecimalFormat("#,###").format(movieSelectedSeats.totalPrice())
        completeButton.isEnabled = movieSelectedSeats.isSelectionComplete()
    }

    override fun navigateToResultView(movieSelectedSeats: MovieSelectedSeats) {
        val seats =
            movieSelectedSeats.selectedSeats.map { seat ->
                ((seat.row + 'A'.code).toChar() + (seat.column + 1).toString())
            }.joinToString(", ")

        Intent(this, MovieResultActivity::class.java).apply {
            putExtra(KEY_MOVIE_ID, intent?.getLongExtra(KEY_MOVIE_ID, INVALID_VALUE_MOVIE_ID))
            putExtra(KEY_MOVIE_DATE, intent?.getStringExtra(KEY_MOVIE_DATE))
            putExtra(KEY_MOVIE_TIME, intent?.getStringExtra(KEY_MOVIE_TIME))
            putExtra(KEY_MOVIE_COUNT, movieSelectedSeats.count)
            putExtra(KEY_MOVIE_SEATS, seats)
            startActivity(this)
        }
    }

    private fun setUpViewById() {
        seatTitle = findViewById(R.id.seatTitle)
        seatPrice = findViewById(R.id.seatPrice)
        completeButton = findViewById(R.id.completeBtn)
    }

    private fun setUpSelectedSeats(selectedPositions: IntArray?) {
        selectedPositions?.forEach { position ->
            seatSelectionPresenter.clickTableSeat(position)
        }
    }

    private fun setUpCompleteButton() {
        completeButton.setOnClickListener {
            displayDialog()
        }
    }
}
