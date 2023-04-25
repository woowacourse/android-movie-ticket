package woowacourse.movie.presentation.view.seatpick

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Dimension
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import com.example.domain.Seat
import com.example.domain.SeatGrade
import com.example.domain.TicketBundle
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivitySeatPickerBinding
import woowacourse.movie.model.BookingCompleteInfo
import woowacourse.movie.model.MovieBookingInfo
import woowacourse.movie.presentation.extension.getParcelableCompat
import woowacourse.movie.presentation.view.bookcomplete.BookCompleteActivity
import woowacourse.movie.presentation.view.common.BackButtonActivity

class SeatPickerActivity : BackButtonActivity() {
    private lateinit var binding: ActivitySeatPickerBinding
    private val ticketBundle = TicketBundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeatPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieBookingInfo: MovieBookingInfo? = intent.getParcelableCompat(
            MOVIE_BOOKING_INFO_SCHEDULE_INTENT_KEY
        )
        processEmptyMovieData(movieBookingInfo)

        setView(movieBookingInfo!!)
    }

    private fun setView(movieBookingInfo: MovieBookingInfo) {
        setBookingInfoView(movieBookingInfo)
        setSeatPickerView(movieBookingInfo)
        binding.tvSeatPickerConfirm.setOnClickListener {
            if (ticketBundle.tickets.size != movieBookingInfo.ticketCount) {
                Toast.makeText(
                    this,
                    getString(R.string.error_seat_picker_ticket_count_not_match).format(
                        movieBookingInfo.ticketCount
                    ),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                showDialog(movieBookingInfo)
            }
        }
    }

    private fun showDialog(movieBookingInfo: MovieBookingInfo) {
        AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.dialog_title_seat_picker_confirm))
            setMessage(getString(R.string.dialog_message_seat_picker_confirm))
            setCancelable(false)
            setPositiveButton(getString(R.string.dialog_positive_button_seat_picker_confirm)) { _, _ ->
                setDialogPositiveEvent(movieBookingInfo)
            }
            setNegativeButton(getString(R.string.dialog_negative_button_seat_picker_confirm)) { dialog, _ ->
                dialog.dismiss()
            }
        }.show()
    }

    private fun setDialogPositiveEvent(movieBookingInfo: MovieBookingInfo) {
        val intent =
            Intent(this@SeatPickerActivity, BookCompleteActivity::class.java).apply {
                putExtra(
                    BookCompleteActivity.BOOKING_COMPLETE_INFO_INTENT_KEY,
                    BookingCompleteInfo(
                        movieBookingInfo,
                        binding.tvSeatPickerTotalPrice.text.toString().toInt(),
                        ticketBundle.getSeatNames().joinToString(", ")
                    )
                )
            }
        startActivity(intent)
    }

    private fun setBookingInfoView(movieBookingInfo: MovieBookingInfo) {
        binding.tvSeatPickerMovieTitle.text = movieBookingInfo.movieInfo.title
        binding.tvSeatPickerTotalPrice.text = 0.toString()
    }

    private fun setSeatPickerView(movieBookingInfo: MovieBookingInfo) {
        binding.tbSeatPicker.children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .forEachIndexed { index, textView ->
                setTextViewOptions(index, textView)
                setSeatPickerClickListener(index, textView, movieBookingInfo)
            }
    }

    private fun setTextViewOptions(index: Int, textView: TextView) {
        val seat = Seat(index)
        textView.text = seat.getSeatName()
        textView.gravity = Gravity.CENTER
        textView.setTextSize(Dimension.SP, 22f)
        val textColor = when (seat.getSeatGrade()) {
            SeatGrade.S_CLASS -> Color.parseColor("#19D358")
            SeatGrade.A_CLASS -> Color.parseColor("#1B48E9")
            SeatGrade.B_CLASS -> Color.parseColor("#E9B21B")
            SeatGrade.NONE -> Color.BLACK
        }
        textView.setTextColor(textColor)
    }

    private fun setSeatPickerClickListener(
        seatIndex: Int,
        textView: TextView,
        movieBookingInfo: MovieBookingInfo,
    ) {
        textView.setOnClickListener {
            setSeatViewStatus(it, seatIndex, movieBookingInfo)

            binding.tvSeatPickerTotalPrice.text =
                ticketBundle.calculateTotalPrice(movieBookingInfo.date, movieBookingInfo.time)
                    .toString()
        }
    }

    private fun setSeatViewStatus(
        it: View,
        seatIndex: Int,
        movieBookingInfo: MovieBookingInfo
    ) {
        if (it.isSelected) {
            it.isSelected = false
            ticketBundle.popTicket(Seat(seatIndex))
            it.setBackgroundColor(Color.WHITE)
        } else if (!it.isSelected) {
            if (ticketBundle.tickets.size == movieBookingInfo.ticketCount) {
                Toast.makeText(
                    this,
                    getString(R.string.error_seat_picker_selected_max).format(movieBookingInfo.ticketCount),
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
            it.isSelected = true
            ticketBundle.putTicket(Seat(seatIndex))
            it.setBackgroundColor(Color.parseColor("#FAFF00"))
        }
    }

    private fun processEmptyMovieData(movieBookingInfo: MovieBookingInfo?) {
        if (movieBookingInfo == null) {
            Toast.makeText(this, getString(R.string.error_intent_message), Toast.LENGTH_SHORT)
                .show()
            this.finish()
        }
    }

    companion object {
        const val MOVIE_BOOKING_INFO_SCHEDULE_INTENT_KEY = "MOVIE_BOOKING_INFO_SCHEDULE_KEY"
    }
}
