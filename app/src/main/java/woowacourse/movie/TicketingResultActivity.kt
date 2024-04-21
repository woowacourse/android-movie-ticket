package woowacourse.movie

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.TicketingActivity.Companion.EXTRA_COUNT
import woowacourse.movie.TicketingActivity.Companion.EXTRA_MOVIE_ID
import woowacourse.movie.TicketingActivity.Companion.EXTRA_TOTAL_PRICE
import woowacourse.movie.presenter.TicketingResultPresenter
import woowacourse.movie.presenter.contract.TicketingResultContract

class TicketingResultActivity : AppCompatActivity(), TicketingResultContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val count = intent.getIntExtra(EXTRA_COUNT, EXTRA_DEFAULT_COUNT)
        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, EXTRA_DEFAULT_MOVIE_ID)
        val totalPrice = intent.getIntExtra(EXTRA_TOTAL_PRICE, EXTRA_DEFAULT_TOTAL_PRICE)

        val ticketingResultPresenter = TicketingResultPresenter(this, count, totalPrice)
        ticketingResultPresenter.initializeTicketingResult(movieId)
    }

    override fun assignInitialView(
        numberOfPeople: Int,
        movieTitle: String,
        movieDate: String,
        price: Int,
    ) {
        val movieTitleText = findViewById<TextView>(R.id.tv_movie_title)
        val movieDateText = findViewById<TextView>(R.id.tv_movie_date)
        val numberOfPeopleText = findViewById<TextView>(R.id.tv_number_of_people)
        val priceText = findViewById<TextView>(R.id.tv_price)

        movieTitleText.text = movieTitle
        movieDateText.text = movieDate
        numberOfPeopleText.text = getString(R.string.text_number_of_people, numberOfPeople)
        priceText.text = getString(R.string.text_price, price)
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val EXTRA_DEFAULT_COUNT = 0
        private const val EXTRA_DEFAULT_MOVIE_ID = -1
        private const val EXTRA_DEFAULT_TOTAL_PRICE = 0
    }
}
