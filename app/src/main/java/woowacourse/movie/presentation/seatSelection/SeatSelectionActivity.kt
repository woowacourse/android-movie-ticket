package woowacourse.movie.presentation.seatSelection

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Seat
import woowacourse.movie.model.SeatClass
import woowacourse.movie.presentation.ticketing.TicketingActivity.Companion.EXTRA_COUNT
import woowacourse.movie.presentation.ticketingResult.TicketingResultActivity

class SeatSelectionActivity : AppCompatActivity(), SeatSelectionContract.View {
    private lateinit var presenter: SeatSelectionPresenter
    private val completeButton by lazy { findViewById<Button>(R.id.btn_complete) }
    private val totalPriceText by lazy { findViewById<TextView>(R.id.tv_total_price) }
    private val seatItems: List<TextView> by lazy {
        findViewById<TableLayout>(R.id.tl_seats).children.filterIsInstance<TableRow>()
            .flatMap { tableRow ->
                tableRow.children.filterIsInstance<TextView>().toList()
            }.toList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, EXTRA_DEFAULT_MOVIE_ID)
        val ticketCount = intent.getIntExtra(EXTRA_COUNT, EXTRA_DEFAULT_TICKET_COUNT)
        presenter = SeatSelectionPresenter(this, movieId, ticketCount)
        presenter.initializeViewData()
        initializeCompleteButton()
    }

    private fun initializeCompleteButton() {
        completeButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("예매 확인")
                .setMessage("정말 예매하시겠습니까?")
                .setPositiveButton("예매 완료") { _, _ ->
                    presenter.navigate()
                }
                .setNegativeButton("취소") { dialog, _ -> dialog.dismiss() }
                .setCancelable(false)
                .show()
        }
    }

    override fun initializeSeats(seats: List<Seat>) {
        seatItems.forEachIndexed { index, textView ->
            val seat = seats[index]
            textView.text = "${('A'.code + seat.row).toChar()}${seat.col + 1}"
            val colorCode =
                when (seat.seatClass) {
                    SeatClass.B_CLASS -> "#8E13EF"
                    SeatClass.A_CLASS -> "#1B48E9"
                    SeatClass.S_CLASS -> "#19D358"
                }
            textView.setTextColor(Color.parseColor(colorCode))
            textView.setOnClickListener { presenter.updateSeatSelection(index) }
        }
    }

    override fun initializeTicketInfo(movie: Movie) {
        findViewById<TextView>(R.id.tv_title).text = movie.title
    }

    override fun updateSelectedSeatUI(index: Int) {
        val clickedColor = ContextCompat.getColor(this, R.color.clickedSeat_bgr)
        seatItems[index].setBackgroundColor(clickedColor)
    }

    override fun updateUnSelectedSeatUI(index: Int) {
        val unClickedColor = ContextCompat.getColor(this, R.color.unClickedSeat_bgr)
        seatItems[index].setBackgroundColor(unClickedColor)
    }

    override fun updateTotalPrice(price: Int) {
        totalPriceText.text = getString(R.string.text_total_price, price)
    }

    override fun showToastMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun setButtonEnabledState(isEnabled: Boolean) {
        completeButton.isEnabled = isEnabled
    }

    override fun navigate(movieId: Int) {
        Intent(this, TicketingResultActivity::class.java).apply {
            putExtra(EXTRA_MOVIE_ID, movieId)
            startActivity(this)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_MOVIE_ID = "movie_id"
        const val EXTRA_DEFAULT_MOVIE_ID = -1
        const val EXTRA_DEFAULT_TICKET_COUNT = -1
    }
}
