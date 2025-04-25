package woowacourse.movie.view.reservation.seat

import android.content.Intent
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.ReservationInfo
import woowacourse.movie.view.Extras
import woowacourse.movie.view.ReservationUiFormatter
import woowacourse.movie.view.reservation.ReservationDialog
import woowacourse.movie.view.reservation.complete.ReservationCompleteActivity

class SeatSelectActivity :
    AppCompatActivity(),
    SeatSelectContract.View {
    private val presenter: SeatSelectPresenter by lazy { SeatSelectPresenter(this) }
    private val reservationDialog by lazy { ReservationDialog() }
    private val titleTextView: TextView by lazy { findViewById(R.id.tv_seat_select_movie_title) }
    private val priceTextView: TextView by lazy { findViewById(R.id.tv_seat_select_total_price) }
    private val confirmButton: TextView by lazy { findViewById(R.id.tv_seat_select_confirm) }
    private val tl: TableLayout by lazy { findViewById(R.id.tl_seat) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat_select)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupSeatView(tl)
        setupConfirmButton()
        presenter.fetchData(this.intent)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showErrorDialog() {
    }

    override fun initReservationInfo(
        title: String,
        price: Int,
    ) {
        titleTextView.text = title
        priceTextView.text = price.toString()
    }

    override fun showSeatCountError(count: Int) {
        Toast
            .makeText(
                this,
                getString(R.string.seat_select_error_count, count),
                Toast.LENGTH_SHORT,
            ).show()
        return
    }

    override fun updateSeatSelected(seatView: TextView) {
        seatView.setBackgroundResource(R.color.yellow)
    }

    override fun updateSeatDeselected(seatView: TextView) {
        seatView.setBackgroundResource(R.color.white)
    }

    override fun updateTotalPrice(totalPrice: Int) {
        priceTextView.text =
            getString(R.string.seat_select_ticket_price).format(
                ReservationUiFormatter().priceToUI(totalPrice),
            )
    }

    override fun updateConfirmButtonState(isEnabled: Boolean) {
        confirmButton.isClickable = isEnabled
        confirmButton.alpha = if (isEnabled) 1f else 0.1f
    }

    override fun showReservationDialog(
        title: String,
        message: String,
    ) {
        reservationDialog.show(
            this,
            title,
            message,
            { dialog -> dialog.dismiss() },
            { _ ->
                presenter.createReservationInfo { reservationInfo ->
                    navigateToComplete(reservationInfo)
                }
            },
        )
    }

    override fun navigateToComplete(reservationInfo: ReservationInfo) {
        val intent =
            Intent(this, ReservationCompleteActivity::class.java).apply {
                putExtra(Extras.ReservationInfoData.RESERVATION_KEY, reservationInfo)
            }
        startActivity(intent)
        finish()
    }

    private fun setupSeatView(tableLayout: TableLayout) {
        for (i in 0 until tableLayout.childCount) {
            val row = tableLayout.getChildAt(i)
            if (row is TableRow) {
                for (j in 0 until row.childCount) {
                    val seatView = row.getChildAt(j)
                    if (seatView is TextView) {
                        val seatId = seatView.text.toString()
                        seatView.tag = seatId
                        seatView.setOnClickListener {
                            presenter.onSeatClicked(seatView)
                        }
                    }
                }
            }
        }
    }

    private fun setupConfirmButton() {
        confirmButton.apply {
            isClickable = false
            alpha = 0.1f
            setOnClickListener {
                presenter.onConfirmClicked(
                    getString(R.string.reservation_dialog_title),
                    getString(R.string.reservation_dialog_message),
                )
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
