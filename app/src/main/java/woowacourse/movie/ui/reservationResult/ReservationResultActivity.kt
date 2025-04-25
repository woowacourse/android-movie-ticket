package woowacourse.movie.ui.reservationResult

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.ui.extensions.serializableData
import woowacourse.movie.ui.factory.CustomAlertDialog
import woowacourse.movie.ui.factory.DialogInfo
import woowacourse.movie.ui.main.MainActivity
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReservationResultActivity : AppCompatActivity(), ReservationResultContract.View {
    private val customAlertDialog = CustomAlertDialog(this)
    private lateinit var cancelGuide: TextView
    private lateinit var title: TextView
    private lateinit var screeningDate: TextView
    private lateinit var ticketCount: TextView
    private lateinit var totalPrice: TextView
    private lateinit var reservationResultPresenter: ReservationResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation_result)
        initSystemUI()
        reservationResultPresenter = ReservationResultPresenter(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViewId()
        initReservationResult()
    }

    private fun initSystemUI() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initViewId() {
        cancelGuide = findViewById(R.id.tv_cancel_guide)
        title = findViewById(R.id.tv_title)
        screeningDate = findViewById(R.id.tv_screening_date)
        ticketCount = findViewById(R.id.tv_ticket_count)
        totalPrice = findViewById(R.id.tv_total_price)
    }

    private fun initReservationResult() {
        val reservation =
            intent.serializableData(
                KEY_RESERVATION_RESULT_ACTIVITY_RESERVATION,
                Reservation::class.java,
            )

        if (reservation == null) {
            showReservationError()
        } else {
            initReservation(reservation)
        }
    }

    private fun showReservationError() {
        val dialogInfo =
            DialogInfo(
                getString(R.string.error_reservation_title),
                getString(R.string.error_reservation_message),
                getString(R.string.confirm),
                null,
                { },
                ::finish,
            )
        customAlertDialog.show(dialogInfo)
    }

    private fun initReservation(reservation: Reservation) {
        val screeningDateView = screeningDate(reservation.reservedTime)

        cancelGuide.text = getString(R.string.cancel_guide, reservation.cancelMinute)
        title.text = reservation.title
        screeningDate.text = screeningDateView
        ticketCount.text = getString(R.string.formatted_ticket_count, reservation.ticketCount)
        totalPrice.text = wonFormat(this).format(reservation.totalPrice())
    }

    private fun screeningDate(reservedDateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern(getString(R.string.date_time_format))
        return formatter.format(reservedDateTime)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun initScreen(reservation: Reservation) {
        initReservation(reservation)
    }

    companion object {
        private fun wonFormat(context: Context) = DecimalFormat(context.getString(R.string.won_format))

        const val KEY_RESERVATION_RESULT_ACTIVITY_RESERVATION =
            "key_reservation_result_activity_reservation"
    }
}
