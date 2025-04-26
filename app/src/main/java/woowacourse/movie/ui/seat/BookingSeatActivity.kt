package woowacourse.movie.ui.seat

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import androidx.core.view.forEachIndexed
import woowacourse.movie.R
import woowacourse.movie.domain.model.BookedTicket
import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.ui.complete.BookingCompleteActivity
import woowacourse.movie.utils.intentSerializable
import java.time.LocalDateTime

class BookingSeatActivity :
    AppCompatActivity(),
    BookingSeatContract.View {
    private val bookingSeatPresenter = BookingSeatPresenter(this)

    private val seatTextViews: MutableMap<String, TextView> = mutableMapOf()
    private val confirmButton: Button by lazy { findViewById(R.id.btn_confirm) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_seat)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        applyWindowInsets()
        setSeatsId()
        bookingSeatPresenter.fetchHeadcount()
        bookingSeatPresenter.fetchMovieTitle()
        bookingSeatPresenter.updateMovieTitle()
        bookingSeatPresenter.updateTotalPrice()
        bookingSeatPresenter.updateConfirmButton()
        setConfirmButtonClickListener()
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun getHeadcount(): Headcount? = intent.intentSerializable(EXTRA_HEADCOUNT, Headcount::class.java)

    override fun getMovieTitle(): String? = intent.getStringExtra(EXTRA_MOVIE_TITLE)

    override fun setTotalPriceTextView(totalPrice: Int) {
        val totalPriceView: TextView = findViewById(R.id.tv_price)
        totalPriceView.text = getString(R.string.text_korean_won).format(totalPrice)
    }

    override fun setMovieTitleTextView(movieTitle: String) {
        val movieTitleView: TextView = findViewById(R.id.tv_movie_title)
        movieTitleView.text = movieTitle
    }

    override fun selectSeat(seatPosition: Pair<Int, Int>) {
        val seatView: TextView? = seatTextViews[seatPosition.toSeatTag()]
        seatView?.setBackgroundColor(getColor(R.color.selected_seat))
    }

    override fun unselectSeat(seatPosition: Pair<Int, Int>) {
        val seatView: TextView? = seatTextViews[seatPosition.toSeatTag()]
        seatView?.setBackgroundColor(getColor(R.color.white))
    }

    private fun setSeatsId() {
        val tableLayout: TableLayout = findViewById(R.id.table_layout_seats)
        tableLayout
            .children
            .filterIsInstance<TableRow>()
            .forEachIndexed { rowIndex, tableRow ->
                tableRow.forEachIndexed { colIndex, textView ->
                    val seatText = "${ASCII_A + rowIndex}${colIndex + 1}"
                    textView.setTag(R.id.seat_tag, seatText)
                    seatTextViews[seatText] = textView as TextView
                    textView.text = seatText

                    when {
                        rowIndex < 2 -> textView.setTextColor(getColor(R.color.seat_b_grade))
                        rowIndex < 4 -> textView.setTextColor(getColor(R.color.seat_s_grade))
                        rowIndex < 5 -> textView.setTextColor(getColor(R.color.seat_a_grade))
                    }
                    textView.setOnClickListener {
                        bookingSeatPresenter.updateSeat(seatText)
                    }
                }
            }
    }

    override fun setConfirmButton(isEnabled: Boolean) {
        confirmButton.isEnabled = isEnabled
    }

    override fun moveToBookingCompleteActivity(
        movieTitle: String,
        headcount: Headcount,
        seats: Seats,
    ) {
        val bookedDateTime =
            intent.intentSerializable(EXTRA_DATETIME, LocalDateTime::class.java)
                ?: LocalDateTime.now()
        val bookedTicket =
            BookedTicket(
                movieTitle,
                headcount,
                bookedDateTime,
                seats,
            )
        startActivity(BookingCompleteActivity.newIntent(this, bookedTicket))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    private fun setConfirmButtonClickListener() {
        confirmButton.setOnClickListener {
            showDialog(
                getString(R.string.text_booking_dialog_title),
                getString(R.string.text_booking_dialog_description),
            )
        }
    }

    private fun showDialog(
        title: String,
        description: String,
    ) {
        AlertDialog
            .Builder(this)
            .setTitle(title)
            .setMessage(description)
            .setPositiveButton(getString(R.string.text_booking_dialog_positive_button)) { _, _ ->
                bookingSeatPresenter.completeBookingSeat()
            }.setNegativeButton(getString(R.string.text_booking_dialog_negative_button)) { dialog, _ ->
                dialog.dismiss()
            }.setCancelable(false)
            .show()
    }

    private fun Pair<Int, Int>.toSeatTag(): String = "${ASCII_A + first}$second"

    companion object {
        fun newIntent(
            context: Context,
            movieTitle: String,
            dateTime: LocalDateTime,
            headcount: Headcount,
        ) = Intent(context, BookingSeatActivity::class.java).apply {
            putExtra(EXTRA_MOVIE_TITLE, movieTitle)
            putExtra(EXTRA_DATETIME, dateTime)
            putExtra(EXTRA_HEADCOUNT, headcount)
        }

        private const val EXTRA_MOVIE_TITLE = "movieTitle"
        private const val EXTRA_DATETIME = "dateTime"
        private const val EXTRA_HEADCOUNT = "headcount"
        private const val ASCII_A = 'A'
    }
}
