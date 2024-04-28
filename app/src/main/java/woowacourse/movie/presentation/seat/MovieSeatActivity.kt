package woowacourse.movie.presentation.seat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieSeat
import woowacourse.movie.presentation.result.MovieResultActivity
import woowacourse.movie.utils.MovieErrorCode
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_ID
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_RESERVATION_COUNT
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_SCREEN_DATE_TIME_ID
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_SEATS_ID_LIST
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_SELECTED_SEAT_INDEXES
import woowacourse.movie.utils.MovieIntentConstants.NOT_FOUND_MOVIE_ID
import woowacourse.movie.utils.MovieIntentConstants.NOT_FOUND_MOVIE_RESERVATION_COUNT
import woowacourse.movie.utils.MovieIntentConstants.NOT_FOUND_MOVIE_SCREEN_DATE_TIME_ID
import woowacourse.movie.utils.formatCurrency
import woowacourse.movie.utils.mapSeatNumberToLetter

class MovieSeatActivity : AppCompatActivity(), MovieSeatContract.View {
    private lateinit var reservationCompleteActivityLauncher: ActivityResultLauncher<Intent>
    private lateinit var movieSeatPresenter: MovieSeatPresenter
    private lateinit var seatButtons: List<Button>

    private val seatTable: TableLayout by lazy { findViewById(R.id.seatTable) }
    private val seatMoviePrice: TextView by lazy { findViewById(R.id.seatMoviePrice) }
    private val seatCompleteBtn: TextView by lazy { findViewById(R.id.seatCompleteBtn) }
    private val seatMovieTitle: TextView by lazy { findViewById(R.id.seatMovieTitle) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_seat)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        reservationCompleteActivityLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == MovieErrorCode.INVALID_MOVIE_ID.code) {
                    Toast.makeText(this, MovieErrorCode.INVALID_MOVIE_ID.msg, Toast.LENGTH_SHORT).show()
                }
            }

        movieSeatPresenter = MovieSeatPresenter(this)

        movieSeatPresenter.loadSeats(
            intent.getLongExtra(
                EXTRA_MOVIE_ID,
                NOT_FOUND_MOVIE_ID,
            ),
            intent.getLongExtra(
                EXTRA_MOVIE_SCREEN_DATE_TIME_ID,
                NOT_FOUND_MOVIE_SCREEN_DATE_TIME_ID,
            ),
            intent.getIntExtra(
                EXTRA_MOVIE_RESERVATION_COUNT,
                NOT_FOUND_MOVIE_RESERVATION_COUNT,
            ),
        )
    }

    override fun onInitView(
        movie: Movie,
        seats: List<MovieSeat>,
    ) {
        seatMovieTitle.text = movie.title
        seatButtons =
            seatTable.children.filterIsInstance<TableRow>().flatMap { it.children }
                .filterIsInstance<Button>().toList()

        seatButtons.forEachIndexed { index, button ->
            button.text = mapSeatNumberToLetter(index)
            button.setOnClickListener {
                movieSeatPresenter.selectSeat(index, seats[index], it.isSelected)
            }
        }
    }

    override fun onSeatUpdate(
        buttonIndex: Int,
        isSelected: Boolean,
    ) {
        seatButtons[buttonIndex].isSelected = isSelected
    }

    override fun onPriceUpdate(totalPrice: Int) {
        seatMoviePrice.text = formatCurrency(totalPrice)
    }

    override fun onReservationButtonChanged(isEnable: Boolean) {
        when (isEnable) {
            true -> {
                with(seatCompleteBtn) {
                    setBackgroundColor(getColor(R.color.purple_500))
                    setOnClickListener {
                        AlertDialog.Builder(this@MovieSeatActivity)
                            .setTitle(getString(R.string.reservation_dialog_title))
                            .setMessage(getString(R.string.reservation_dialog_content))
                            .setPositiveButton(getString(R.string.reservation_complete)) { _, _ ->
                                movieSeatPresenter.reservation()
                            }
                            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                                dialog.dismiss()
                            }
                            .setCancelable(false)
                            .show()
                    }
                }
            }
            false -> {
                with(seatCompleteBtn) {
                    setBackgroundColor(getColor(R.color.gray))
                    setOnClickListener { }
                }
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let {
            (it.getSerializable(EXTRA_MOVIE_SELECTED_SEAT_INDEXES) as SeatUiModel).selectedSeat.forEachIndexed { index, movieSeat ->
                movieSeatPresenter.selectSeat(movieSeat.number, movieSeat, false)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(EXTRA_MOVIE_SELECTED_SEAT_INDEXES, movieSeatPresenter.uiModel)
    }

    override fun onReservationComplete(
        movieId: Long,
        movieScreenDateTimeId: Long,
        movieSeatIds: List<Long>,
        count: Int,
        totalPrice: Int,
    ) {
        Intent(this, MovieResultActivity::class.java).apply {
            putExtra(EXTRA_MOVIE_ID, movieId)
            putExtra(EXTRA_MOVIE_SCREEN_DATE_TIME_ID, movieScreenDateTimeId)
            putExtra(EXTRA_MOVIE_SEATS_ID_LIST, movieSeatIds.toLongArray())
            reservationCompleteActivityLauncher.launch(this)
        }
    }

    override fun onError(errorCode: MovieErrorCode) {
        // 에러 발생 시에, 이전 액티비티로 이동하며 메세지 전달
        setResult(errorCode.code)
        finish()
    }
}
