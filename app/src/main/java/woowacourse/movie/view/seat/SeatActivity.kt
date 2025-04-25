package woowacourse.movie.view.seat

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.view.booking.BookingActivity.Companion.KEY_BOOKING
import woowacourse.movie.view.ext.getSerializable
import woowacourse.movie.view.seat.SeatContract.PresenterFactory
import woowacourse.movie.view.seat.model.coord.Column
import woowacourse.movie.view.seat.model.coord.Coordination
import woowacourse.movie.view.seat.model.coord.Row

class SeatActivity : AppCompatActivity(), SeatContract.View {
    private val presenter by lazy { PresenterFactory.providePresenter(this) }
    private val seat by lazy { findViewById<TableLayout>(R.id.seatTable) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat)

        intent.getSerializable(KEY_BOOKING, Booking::class.java)?.let {
        }
        initView("it.title")
    }

    private fun initView(movieTitle: String) {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initMovieTitle(movieTitle)
        initSeat()
    }

    private fun initMovieTitle(movieTitle: String) {
        findViewById<TextView>(R.id.tv_title).text = movieTitle
    }

    private fun initSeat() {
        seat
            .children
            .filterIsInstance<TableRow>()
            .forEachIndexed { rowIndex, row ->
                setRowListener(row, rowIndex)
            }
    }

    private fun setRowListener(
        row: TableRow,
        rowIndex: Int,
    ) {
        row.children
            .forEachIndexed { col, view ->
                val newTag = Coordination(Column(rowIndex + 1), Row(col + 1))
                view.tag = newTag
                view.setOnClickListener {
                    onClickSeat(it.tag as Coordination)
                }
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClickSeat(position: Coordination) {
        presenter.changeSeat(position)
    }

    override fun showSeat(seat: List<Coordination>) {
        this.seat.children
            .filterIsInstance<TableRow>()
            .forEach { row ->
                row.children
                    .filterIsInstance<TextView>()
                    .forEach { textView ->
                        val seatCoord = textView.tag as? Coordination
                        if (seatCoord != null && seat.contains(seatCoord)) {
                            textView.setBackgroundColor(Color.YELLOW)
                        } else {
                            textView.setBackgroundColor(0)
                        }
                    }
            }
    }
}
