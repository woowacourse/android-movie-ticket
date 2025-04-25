package woowacourse.movie.seat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import woowacourse.movie.KeyIdentifiers
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Point
import woowacourse.movie.domain.Reservation
import woowacourse.movie.ext.getSerializableCompat
import woowacourse.movie.result.ReservationResultActivity
import java.text.DecimalFormat

class SeatActivity : AppCompatActivity(), SeatContract.View {
    private val selectButton: Button by lazy { findViewById(R.id.btn_select) }
    private val presenter = SeatPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        presenter.initReservation(getReservation())
        presenter.initView()
        initSelectButtonClick()
    }

    private fun getReservation(): Reservation {
        return intent.getSerializableCompat<Reservation>(KeyIdentifiers.KEY_RESERVATION)
    }

    override fun initSeat() {
        val seat = findViewById<TableLayout>(R.id.tl_seat)

        seat.children.filterIsInstance<TableRow>().forEachIndexed { rowIndex, row ->
            row.children.filterIsInstance<TextView>()
                .forEachIndexed { colIndex, view ->
                    val point = presenter.getPoint(rowIndex, colIndex)
                    view.tag = point

                    view.text = getString(R.string.seat_point).format('A' + point.x, point.y + 1)
                    initSeatClickAction(view, point)
                }
        }
    }

    private fun initSeatClickAction(
        view: TextView,
        point: Point,
    ) {
        view.setOnClickListener {
            if (presenter.isOccupied(point)) {
                presenter.cancelSelection(point)
                view.setBackgroundColor(getColor(R.color.white))
            } else {
                presenter.selectSeat(point)
                view.setBackgroundColor(getColor(R.color.yellow))
            }
            updateButtonState()
        }
    }

    private fun updateButtonState() {
        if (presenter.canClickButton()) {
            selectButton.setBackgroundColor(getColor(R.color.purple_500))
            selectButton.isClickable = true
        } else {
            selectButton.setBackgroundColor(getColor(R.color.gray))
            selectButton.isClickable = false
        }
    }

    override fun showMovieInfo(movie: Movie) {
        val title = findViewById<TextView>(R.id.tv_title)
        title.text = movie.title
    }

    override fun initSelectButtonClick() {
        selectButton.setOnClickListener {
            if (presenter.canReserve()) {
                showSelectDialog()
            } else {
                Toast.makeText(this, R.string.seat_not_match_count, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showSelectDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.reserve_dialog_title))
            .setMessage(getString(R.string.reserve_dialog_message))
            .setPositiveButton(getString(R.string.reserve_dialog_positive_button)) { _, _ ->
                val intent = ReservationResultActivity.newIntent(this, presenter.reservation)
                startActivity(intent)
            }
            .setNegativeButton(getString(R.string.reserve_dialog_negative_button)) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    override fun updateTotalPrice(price: Int) {
        val totalPrice = findViewById<TextView>(R.id.tv_total_price)
        totalPrice.text = getString(R.string.formatted_total_price).format(decimal.format(price))
    }

    companion object {
        private val decimal = DecimalFormat("#,###")

        fun newIntent(
            context: Context,
            reservation: Reservation,
        ): Intent =
            Intent(context, SeatActivity::class.java).apply {
                putExtra(KeyIdentifiers.KEY_RESERVATION, reservation)
            }
    }
}
