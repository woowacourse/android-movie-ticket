package woowacourse.movie.seat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
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
import java.text.DecimalFormat

class SeatActivity : AppCompatActivity(), SeatContract.View {
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
        initSeat()
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
                    setClickListener(view, point)
                }
        }
    }

    private fun setClickListener(
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
        }
    }

    override fun showMovieInfo(movie: Movie) {
        val title = findViewById<TextView>(R.id.tv_title)
        title.text = movie.title
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
