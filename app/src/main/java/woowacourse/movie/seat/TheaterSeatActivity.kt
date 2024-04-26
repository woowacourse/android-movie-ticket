package woowacourse.movie.seat

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.model.theater.Seat

@Suppress("DEPRECATION")
@SuppressLint("DiscouragedApi")
class TheaterSeatActivity : AppCompatActivity(), TheaterSeatContract.View {
    private lateinit var presenter: TheaterSeatPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seat)
        initializePresenter()
        setupSeats()
    }

    private fun initializePresenter() {
        val intent = intent
        val ticketNum = intent.getIntExtra("ticketNum", 0)
        presenter = TheaterSeatPresenter(this, ticketNum)
    }

    private fun setupSeats() {
        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)
        tableLayout.children.filterIsInstance<TableRow>()
            .forEach { row ->
                row.children.filterIsInstance<Button>()
                    .forEach { button ->
                        button.setOnClickListener {
                            presenter.toggleSeatSelection(button.text.toString())
                        }
                    }
            }
    }

    override fun updateSeatDisplay(seat: Seat) {
        val buttonId = resources.getIdentifier("${seat.row}${seat.number}", "id", packageName)
        val button = findViewById<Button>(buttonId)
        val color = if (seat.chosen) Color.RED else Color.WHITE
        button.setBackgroundColor(color)
    }

    override fun showConfirmationDialog() {
        // Implementation of dialog showing logic
    }

    override fun setSeatBackground(seatId: String, color: String) {
        val buttonId = resources.getIdentifier(seatId, "id", packageName)
        val button = findViewById<Button>(buttonId)
        val colorInt = Color.parseColor(color)
        button.setBackgroundColor(colorInt)
    }
}
