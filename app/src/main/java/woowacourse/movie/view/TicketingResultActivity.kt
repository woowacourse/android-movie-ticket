package woowacourse.movie.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.presenter.TicketingResultPresenter
import woowacourse.movie.presenter.contract.TicketingResultContract
import woowacourse.movie.view.SeatSelectionActivity.Companion.EXTRA_MOVIE_ID
import woowacourse.movie.view.SeatSelectionActivity.Companion.EXTRA_NUM_TICKET
import woowacourse.movie.view.SeatSelectionActivity.Companion.EXTRA_PRICE
import woowacourse.movie.view.TicketingActivity.Companion.EXTRA_DATE
import woowacourse.movie.view.TicketingActivity.Companion.EXTRA_TIME
import java.time.LocalDate

class TicketingResultActivity : AppCompatActivity(), TicketingResultContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val count = intent.getIntExtra(EXTRA_NUM_TICKET, EXTRA_DEFAULT_COUNT)
        val movieId = intent.getLongExtra(EXTRA_MOVIE_ID, EXTRA_DEFAULT_MOVIE_ID)
        val totalPrice = intent.getIntExtra(EXTRA_PRICE, EXTRA_DEFAULT_TOTAL_PRICE)
        val date = intent.getStringExtra(EXTRA_DATE)
        val time = intent.getStringExtra(EXTRA_TIME)

        val ticketingResultPresenter = TicketingResultPresenter(this)
        ticketingResultPresenter.initializeTicketingResult(movieId, count, totalPrice)
    }

    override fun assignInitialView(
        numberOfPeople: Int,
        movieTitle: String,
        movieDate: LocalDate,
        totalPrice: Int,
    ) {
        val movieTitleText = findViewById<TextView>(R.id.tv_movie_title)
        val movieDateText = findViewById<TextView>(R.id.tv_movie_date)
        val numberOfPeopleText = findViewById<TextView>(R.id.tv_number_of_people)
        val priceText = findViewById<TextView>(R.id.tv_price)

        movieTitleText.text = movieTitle
        movieDateText.text = movieDate.toString()
        numberOfPeopleText.text = getString(R.string.text_number_of_people, numberOfPeople)
        priceText.text = getString(R.string.text_price, totalPrice)
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val EXTRA_DEFAULT_COUNT = 0
        private const val EXTRA_DEFAULT_MOVIE_ID = -1L
        private const val EXTRA_DEFAULT_TOTAL_PRICE = 0
    }
}
