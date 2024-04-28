package woowacourse.movie.ui.seatselection

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import woowacourse.movie.R
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.SeatGrade
import woowacourse.movie.repository.DummySeatList
import woowacourse.movie.repository.SeatListRepository
import woowacourse.movie.ui.result.ResultActivity
import java.io.Serializable

class SeatSelectionActivity : AppCompatActivity() {
    private lateinit var seatLayout: TableLayout
    private lateinit var ticket: Ticket
    private lateinit var title: String

    private lateinit var titleTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var confirmTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        title = intent.getStringExtra("screenTitle") ?: ""
        ticket = intent.intentSerializable("ticket", Ticket::class.java) ?: Ticket(-1)
        seatLayout = findViewById(R.id.seat_tablelayout)
        initView()
        initSeatBoard(DummySeatList)
    }

    private fun <T : Serializable> Intent.intentSerializable(
        key: String,
        clazz: Class<T>,
    ): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.getSerializableExtra(key, clazz)
        } else {
            this.getSerializableExtra(key) as T?
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    fun initView() {
        titleTextView = findViewById(R.id.selection_title_textview)
        priceTextView = findViewById(R.id.selection_price_textview)
        confirmTextView = findViewById(R.id.selection_confirm_textview)

        titleTextView.text = title
    }

    private fun initSeatBoard(seatBoardDb: SeatListRepository) {
        val seats = seatBoardDb.seatBoards[0].seats
        seatLayout.children.filterIsInstance<TableRow>().flatMap { it.children }
            .filterIsInstance<TextView>().forEachIndexed { idx, view ->
                view.text = "${seats[idx].row}${seats[idx].col}"
                view.setTextColor(seats[idx].seatGrade.toColor())
            }
        initClickListener(seats)
    }

    fun initClickListener(seats: List<Seat>) {
        seatLayout.children.filterIsInstance<TableRow>().flatMap { it.children }
            .filterIsInstance<TextView>().forEachIndexed { idx, view ->
                view.setOnClickListener {
                    val backgroundColor = view.background as ColorDrawable

                    if (backgroundColor.color == getColor(R.color.seat_selected)) {
                        view.setBackgroundColor(getColor(R.color.seat_unselected))
                        ticket.seats.delete(seats[idx])
                    } else {
                        if (!countIsSufficient()) {
                            if (backgroundColor.color == getColor(R.color.seat_unselected)) {
                                view.setBackgroundColor(getColor(R.color.seat_selected))
                                ticket.seats.add(seats[idx])
                            }
                        }
                    }
                    updatePriceTextView()
                    updateConfirmView()
                }
            }

        confirmTextView.setOnClickListener {
            if ((it.background as ColorDrawable).color == getColor(R.color.confirm_activated)) {
                MaterialAlertDialogBuilder(this)
                    .setTitle("예매 확인")
                    .setMessage("정말 예매하시겠습니까?")
                    .setNegativeButton("취소") { _, _ -> }
                    .setPositiveButton("예매 완료") { _, _ ->
                        val intent = Intent(this@SeatSelectionActivity, ResultActivity::class.java)
                        intent.putExtra("screenTitle", title)
                        intent.putExtra("ticket", ticket)
                        startActivity(intent)
                    }
                    .show()
            }
        }
    }

    private fun updatePriceTextView() {
        priceTextView.text = ticket.seats.totalPrice.toString()
    }

    private fun updateConfirmView() {
        if (!countIsSufficient()) confirmTextView.setBackgroundColor(getColor(R.color.confirm_deactivated))
        else confirmTextView.setBackgroundColor(getColor(R.color.confirm_activated))
    }

    private fun SeatGrade.toColor(): Int {
        return when (this) {
            SeatGrade.A -> getColor(R.color.seat_indigo)
            SeatGrade.S -> getColor(R.color.seat_green)
            SeatGrade.B -> getColor(R.color.seat_purple)
        }
    }

    private fun countIsSufficient(): Boolean =
        ticket.count <= ticket.seats.seatList.size

}
