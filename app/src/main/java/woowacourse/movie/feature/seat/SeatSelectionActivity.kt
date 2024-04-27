package woowacourse.movie.feature.seat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.feature.completed.ReservationCompletedActivity
import woowacourse.movie.feature.main.ui.ScreeningModel
import woowacourse.movie.feature.reservation.ui.SeatModel
import woowacourse.movie.feature.reservation.ui.SeatView

class SeatSelectionActivity : AppCompatActivity(), SeatSelectionContract.View {
    private val movieId by lazy {
        val a = intent.getLongExtra(MOVIE_ID, -1L)
        Log.d("테스트", a.toString())
        a
    }
    private val datePosition by lazy { intent.getIntExtra(DATE_POSITION, -1) }
    private val timePosition by lazy { intent.getIntExtra(TIME_POSITION, -1) }
    private val quantity by lazy { intent.getIntExtra(QUANTITY, -1) }
    private val seatList: MutableList<String> = emptyList<String>().toMutableList()
    private val presenter by lazy {
        SeatSelectionPresenter(
            this,
            movieId,
            datePosition,
            timePosition,
        )
    }

    private val tmpTv by lazy { findViewById<TextView>(R.id.tv_screen_movie_title) }
    private val seatTextViews: SeatView by lazy { createSeatTextViews().let(::SeatView) }
    private val seatConfirmText by lazy { findViewById<TextView>(R.id.btn_seat_confirm) }

    private fun createSeatTextViews(): List<TextView> =
        findViewById<TableLayout>(R.id.table_seat)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .toList()

    private val seatModels: List<SeatModel> =
        listOf(
            SeatModel("A", 1, "B"),
            SeatModel("A", 2, "B"),
            SeatModel("A", 3, "B"),
            SeatModel("A", 4, "B"),
            SeatModel("B", 1, "B"),
            SeatModel("B", 2, "B"),
            SeatModel("B", 3, "B"),
            SeatModel("B", 4, "B"),
            SeatModel("C", 1, "S"),
            SeatModel("C", 2, "S"),
            SeatModel("C", 3, "S"),
            SeatModel("C", 4, "S"),
            SeatModel("D", 1, "S"),
            SeatModel("D", 2, "S"),
            SeatModel("D", 3, "S"),
            SeatModel("D", 4, "S"),
            SeatModel("E", 1, "A"),
            SeatModel("E", 2, "A"),
            SeatModel("E", 3, "A"),
            SeatModel("E", 4, "A"),
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.fetchData()
    }

    override fun initialize(movie: ScreeningModel) {
        tmpTv.text = movie.title
        seatTextViews.initText(seatModels)
        seatTextViews.setupClickListener { position, textView ->
            if (textView.isSelected) {
                seatList.remove(textView.text.toString())
                textView.isSelected = !textView.isSelected
            } else {
                if (seatList.size < quantity) {
                    seatList.add(textView.text.toString())
                    textView.isSelected = !textView.isSelected
                } else {
                    Toast.makeText(this, "${quantity}개의 좌석을 선택해 주세요.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        seatConfirmText.setOnClickListener {
            if (seatList.size == quantity) {
                presenter.saveReservation(seatList)
            } else {
                Toast.makeText(this, "${quantity}개의 좌석을 선택해 주세요.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun navigateToReservationCompleted(reservationId: Long) {
        startActivity(ReservationCompletedActivity.getIntent(this, reservationId))
    }

    companion object {
        const val MOVIE_ID = "movie_id"
        const val TIME_POSITION = "time_position"
        const val DATE_POSITION = "date_position"
        const val QUANTITY = "quantity"

        fun getIntent(
            context: Context,
            movieId: Long,
            datePosition: Int,
            timePosition: Int,
            quantity: Int,
        ): Intent {
            return Intent(context, SeatSelectionActivity::class.java).apply {
                putExtra(MOVIE_ID, movieId)
                putExtra(DATE_POSITION, datePosition)
                putExtra(TIME_POSITION, timePosition)
                putExtra(QUANTITY, quantity)
            }
        }
    }
}
