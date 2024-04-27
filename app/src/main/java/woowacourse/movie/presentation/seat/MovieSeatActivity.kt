package woowacourse.movie.presentation.seat

import android.content.Intent
import android.graphics.Color
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
import woowacourse.movie.utils.MovieIntentConstants
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_ID
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_RESERVATION_COUNT
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_SCREEN_DATE_TIME_ID
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_SEATS_ID_LIST
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_TOTAL_PRICE
import woowacourse.movie.utils.formatCurrency

class MovieSeatActivity : AppCompatActivity(), MovieSeatContract.View {
    private lateinit var reservationCompleteActivityLauncher: ActivityResultLauncher<Intent>
    private lateinit var movieSeatPresenter: MovieSeatPresenter

    private val seatTable: TableLayout by lazy { findViewById(R.id.seatTable) }
    private val seatMoviePrice: TextView by lazy { findViewById(R.id.seatMoviePrice) }
    private val seatCompleteBtn: TextView by lazy { findViewById(R.id.seatCompleteBtn) }
    private val seatMovieTitle: TextView by lazy { findViewById(R.id.seatMovieTitle) }

    private lateinit var seatButtons: List<Button>

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

        movieSeatPresenter.display(
            intent.getLongExtra(
                MovieIntentConstants.EXTRA_MOVIE_ID,
                MovieIntentConstants.NOT_FOUND_MOVIE_ID,
            ),
            intent.getLongExtra(
                MovieIntentConstants.EXTRA_MOVIE_SCREEN_DATE_TIME_ID,
                MovieIntentConstants.NOT_FOUND_MOVIE_SCREEN_DATE_TIME_ID,
            ),
            intent.getIntExtra(
                MovieIntentConstants.EXTRA_MOVIE_RESERVATION_COUNT,
                MovieIntentConstants.NOT_FOUND_MOVIE_RESERVATION_COUNT,
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
            button.setOnClickListener {
                movieSeatPresenter.clickSeat(index, seats[index], it.isSelected)
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
                    setBackgroundColor(Color.MAGENTA)
                    setOnClickListener {
                        AlertDialog.Builder(this@MovieSeatActivity)
                            .setTitle("예매 확인")
                            .setMessage("정말 예매하시겠습니까?")
                            .setPositiveButton("예매 완료") { _, _ ->
                                movieSeatPresenter.reservation()
                            }
                            .setNegativeButton("취소") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .setCancelable(false)
                            .show()
                    }
                }
            }
            false -> {
                with(seatCompleteBtn) {
                    setBackgroundColor(Color.GRAY)
                    setOnClickListener { }
                }
            }
        }
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
            putExtra(EXTRA_MOVIE_TOTAL_PRICE, totalPrice)
            putExtra(EXTRA_MOVIE_RESERVATION_COUNT, count)
            reservationCompleteActivityLauncher.launch(this)
        }
    }

    override fun onError(errorCode: MovieErrorCode) {
        // 에러 발생 시에, 이전 액티비티로 이동하며 메세지 전달
        setResult(errorCode.code)
        finish()
    }
}
