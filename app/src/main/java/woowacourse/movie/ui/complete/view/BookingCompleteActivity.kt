package woowacourse.movie.ui.complete.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.BookedTicket
import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.ui.complete.contract.BookingCompleteContract
import woowacourse.movie.ui.complete.presenter.BookingCompletePresenter
import woowacourse.movie.ui.movielist.view.MovieListActivity
import woowacourse.movie.utils.StringFormatter
import woowacourse.movie.utils.intentSerializable
import java.time.LocalDateTime

class BookingCompleteActivity :
    AppCompatActivity(),
    BookingCompleteContract.View {
    private val bookingCompletePresenter = BookingCompletePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_complete)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        applyWindowInsets()
        initializeFromIntent()
        bookingCompletePresenter.updateViews()
        setOnBackPressedCallback()
    }

    private fun initializeFromIntent() {
        val bookedTicket =
            intent.intentSerializable(EXTRA_BOOKED_TICKET, BookedTicket::class.java)
                ?: BookedTicket("", Headcount(), LocalDateTime.MIN, Seats())
        bookingCompletePresenter.loadBookedTicket(bookedTicket)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                moveToMovieListActivity()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    override fun setBookedTicket(bookedTicket: BookedTicket) {
        val movieNameTextView: TextView = findViewById(R.id.tv_title)
        val releaseDateTextView: TextView = findViewById(R.id.tv_release_date)
        val headcountTextView: TextView = findViewById(R.id.tv_headcount)

        with(bookedTicket) {
            releaseDateTextView.text = StringFormatter.dateTimeFormat(dateTime)
            movieNameTextView.text = movieName
            headcountTextView.text =
                getString(R.string.text_headcount_with_seats).format(
                    headcount.count,
                    seats.seats
                        .map { it.toText() }
                        .sorted()
                        .joinToString(),
                )
        }
    }

    override fun setBookedTicketPrice(price: Int) {
        val priceFormat: String = StringFormatter.thousandFormat(price)
        val priceTextView: TextView = findViewById(R.id.tv_price)
        priceTextView.text = getString(R.string.text_on_site_payment).format(priceFormat)
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun moveToMovieListActivity() {
        val intent =
            Intent(this, MovieListActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        startActivity(intent)
        finish()
    }

    private fun setOnBackPressedCallback() {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    moveToMovieListActivity()
                }
            },
        )
    }

    private fun Seat.toText(): String = Char(row + ASCII_A.code) + (col + 1).toString()

    companion object {
        fun newIntent(
            context: Context,
            bookedTicket: BookedTicket,
        ): Intent =
            Intent(context, BookingCompleteActivity::class.java).apply {
                putExtra(EXTRA_BOOKED_TICKET, bookedTicket)
            }

        private const val EXTRA_BOOKED_TICKET = "bookedTicket"
        private const val ASCII_A = 'A'
    }
}
