package woowacourse.movie.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.presenter.TicketingResultPresenter
import woowacourse.movie.presenter.contract.TicketingResultContract
import woowacourse.movie.view.SeatSelectionActivity.Companion.EXTRA_TICKETING_RESULT
import woowacourse.movie.view.state.TicketingResult
import java.time.format.DateTimeFormatter

class TicketingResultActivity : AppCompatActivity(), TicketingResultContract.View {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val ticketingResultPresenter = TicketingResultPresenter(this)
        val ticketingResult =
            intent.getParcelableExtra(EXTRA_TICKETING_RESULT, TicketingResult::class.java)
        ticketingResultPresenter.initializeTicketingResult(ticketingResult)
        initializeOnBackPressedCallback()
    }

    override fun assignInitialView(ticketingResult: TicketingResult) {
        val movieTitleText = findViewById<TextView>(R.id.tv_movie_title)
        val movieDateText = findViewById<TextView>(R.id.tv_movie_date)
        val numberOfPeopleAndSeatsText = findViewById<TextView>(R.id.tv_number_of_people_seats)
        val priceText = findViewById<TextView>(R.id.tv_price)

        val shownSeats =
            ticketingResult.seats.map {
                getString(
                    R.string.text_seat_position,
                    convertRowNumberIntoChar(it.row),
                    it.column + 1,
                )
            }
        val shownDate =
            ticketingResult.date.format(DateTimeFormatter.ofPattern(getString(R.string.format_datetime)))

        movieTitleText.text = ticketingResult.movieTitle
        movieDateText.text = getString(R.string.text_reserved_datetime, shownDate, ticketingResult.time.toString())
        numberOfPeopleAndSeatsText.text =
            getString(
                R.string.text_reserved_count_seats,
                ticketingResult.numberOfTickets,
                shownSeats.joinToString(SEPARATOR_SEATS),
            )
        priceText.text = getString(R.string.text_price, ticketingResult.price)
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) navigateBackToScreeningList()
        return super.onOptionsItemSelected(item)
    }

    private fun initializeOnBackPressedCallback() {
        val onBackPressedCallBack =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = navigateBackToScreeningList()
            }
        onBackPressedDispatcher.addCallback(onBackPressedCallBack)
    }

    private fun navigateBackToScreeningList() {
        Intent(this@TicketingResultActivity, MovieListActivity::class.java).also {
            it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(it)
        }
    }

    private fun convertRowNumberIntoChar(rowIndex: Int) = (START_ROW_CHAR.code + rowIndex).toChar()

    companion object {
        private const val SEPARATOR_SEATS = ", "
        private const val START_ROW_CHAR = 'A'
    }
}
