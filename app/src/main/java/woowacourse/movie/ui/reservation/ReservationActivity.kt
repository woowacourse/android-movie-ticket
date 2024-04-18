package woowacourse.movie.ui.reservation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import woowacourse.movie.R
import woowacourse.movie.domain.repository.DummyReservation
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class ReservationActivity : AppCompatActivity() {
    private val viewModel: ReservationViewModel by lazy { ReservationViewModel(DummyReservation) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        val id = intent.getIntExtra(PUT_EXTRA_KEY_RESERVATION_ID, DEFAULT_RESERVATION_ID)
        handleState(viewModel.loadReservation(id))
    }

    private fun handleState(state: ReservationEventState) {
        when (state) {
            is ReservationEventState.Success -> {
                when (state) {
                    is ReservationEventState.Success.ReservationLoading -> initBinding(state)
                }
            }

            is ReservationEventState.Failure -> {
                when (state) {
                    is ReservationEventState.Failure.GoToBack -> {
                        Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                        finish()
                    }

                    is ReservationEventState.Failure.UnexpectedFinish -> {
                        Snackbar.make(findViewById(android.R.id.content), state.message, Snackbar.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }

    private fun initBinding(state: ReservationEventState.Success.ReservationLoading) {
        val title = findViewById<TextView>(R.id.tv_reservation_title)
        val date = findViewById<TextView>(R.id.tv_reservation_date)
        val count = findViewById<TextView>(R.id.tv_reservation_count)
        val amount = findViewById<TextView>(R.id.tv_reservation_amount)

        with(state.reservation) {
            title.text = screen.movie.title
            date.text = screen.date
            count.text = getString(R.string.reserve_count).format(this.count)
            amount.text =
                getString(R.string.reserve_amount).format(
                    when (Locale.getDefault().country) {
                        Locale.KOREA.country -> DecimalFormat("#,###원").format(totalPrice)
                        else -> {
                            NumberFormat.getCurrencyInstance(Locale.getDefault()).format(totalPrice)
                        }
                    },
                )
        }
    }

    companion object {
        private const val PUT_EXTRA_KEY_RESERVATION_ID = "reservationId"
        private const val DEFAULT_RESERVATION_ID = -1

        fun startActivity(
            context: Context,
            reservationId: Int,
        ) {
            val intent = Intent(context, ReservationActivity::class.java)
            intent.putExtra(PUT_EXTRA_KEY_RESERVATION_ID, reservationId)
            context.startActivity(intent)
        }
    }
}
