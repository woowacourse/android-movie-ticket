package woowacourse.movie.activity.reservation

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivitySeatBinding
import woowacourse.movie.dto.ReservationDto
import woowacourse.movie.dto.SeatDto
import woowacourse.movie.global.ServiceLocator

class ReservationSeatActivity : AppCompatActivity(), ReservationSeatContract.View {
    private val binding: ActivitySeatBinding by lazy {
        ActivitySeatBinding.inflate(layoutInflater)
    }
    private val presenter: ReservationSeatContract.Presenter = ServiceLocator.reservationSeatPresenter(this)

    private var totalPrice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.seat)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        presenter.initSeatTable()
    }

    override fun setButtonState(state: Boolean) {
        binding.submit.isEnabled = state
    }

    override fun initSeatTable(seats: List<SeatDto>) {
        binding.submit.isEnabled = false
        val rows = seats.groupBy { it.row }

        rows.keys
            .sorted()
            .forEach { row ->
                val tableRow = layoutInflater.inflate(R.layout.seat_table_row, binding.root, false) as TableRow
                rows[row]?.forEach { seat ->
                    (layoutInflater.inflate(R.layout.seat_item, tableRow, false) as TextView)
                        .apply {
                            text = seat.location
                            getColorResIdForRankTag(seat, this)
                            setOnClickListener {
                                whenSeatClicked(seat, this)
                            }
                        }.let {
                            tableRow.addView(it)
                        }
                }
                binding.table.addView(tableRow)
            }
    }

    private fun whenSeatClicked(
        seat: SeatDto,
        view: TextView,
    ) {
        when (view.isSelected) {
            true -> {
                view.isSelected = false
                totalPrice -= seat.price.price
                view.setBackgroundColor(getColor(R.color.white))
            }
            false -> {
                view.isSelected = true
                totalPrice += seat.price.price
                view.setBackgroundColor(getColor(R.color.seat_selected))
            }
        }
        binding.total.text = getString(R.string.total_price_general, totalPrice)
        presenter.setButtonState(totalPrice)
    }

    private fun getColorResIdForRankTag(
        seat: SeatDto,
        view: TextView,
    ) {
        when (seat.price.tag) {
            "RANK_A" -> view.setTextColor(getColor(R.color.seat_rank_a))
            "RANK_S" -> view.setTextColor(getColor(R.color.seat_rank_s))
            "RANK_B" -> view.setTextColor(getColor(R.color.seat_rank_b))
            else -> view.setTextColor(getColor(R.color.gray_button_background))
        }
    }

    companion object {
        private const val BOOKING_STATUS_KEY = "bookingStatus"

        fun newIntent(
            from: Context,
            dto: ReservationDto,
        ): Intent {
            return Intent(from, ReservationSeatActivity::class.java).apply {
                putExtra(BOOKING_STATUS_KEY, dto)
            }
        }
    }
}

private class DialogBuilder(val context: Context) {
    private var dialog: AlertDialog.Builder =
        AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.complete_dialog_title))
            .setMessage(context.getString(R.string.complete_dialog_message))
            .setNegativeButton(R.string.complete_dialog_negative_button) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)

    fun onPositiveButtonClicked(block: () -> Unit): DialogBuilder {
        dialog.setPositiveButton(context.getString(R.string.complete_dialog_positive_button)) { _, _ ->
            block()
        }
        return this
    }

    fun build(): AlertDialog = dialog.create()
}
