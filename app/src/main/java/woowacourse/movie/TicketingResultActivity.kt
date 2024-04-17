package woowacourse.movie

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.TicketingActivity.Companion.EXTRA_MOVIE_ID
import woowacourse.movie.TicketingActivity.Companion.EXTRA_NUMBER_OF_PEOPLE
import woowacourse.movie.presenter.TicketingResultPresenter
import woowacourse.movie.presenter.contract.TicketingResultContract

class TicketingResultActivity : AppCompatActivity(), TicketingResultContract {
    private lateinit var ticketingResultPresenter: TicketingResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing_result)
        val numberOfPeople = intent.getIntExtra(EXTRA_NUMBER_OF_PEOPLE, 0)
        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        ticketingResultPresenter = TicketingResultPresenter(this, movieId, numberOfPeople)
        ticketingResultPresenter.assignInitialView()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}
