package woowacourse.movie

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.TicketingActivity.Companion.EXTRA_MOVIE_DATE
import woowacourse.movie.TicketingActivity.Companion.EXTRA_MOVIE_TITLE
import woowacourse.movie.TicketingActivity.Companion.EXTRA_NUMBER_OF_PEOPLE

class TicketingResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing_result)

        val movieTitleText = findViewById<TextView>(R.id.tv_movie_title)
        val movieDateText = findViewById<TextView>(R.id.tv_movie_date)
        val numberOfPeopleText = findViewById<TextView>(R.id.tv_number_of_people)
        val priceText = findViewById<TextView>(R.id.tv_price)

        intent.apply {
            val numberOfPeople = getIntExtra(EXTRA_NUMBER_OF_PEOPLE, 0)
            movieTitleText.text = getStringExtra(EXTRA_MOVIE_TITLE)
            movieDateText.text = getStringExtra(EXTRA_MOVIE_DATE)
            numberOfPeopleText.text = getString(R.string.text_number_of_people, numberOfPeople)
            val price = MOVIE_PRICE * numberOfPeople
            priceText.text = getString(R.string.text_price, price)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val MOVIE_PRICE = 13000
    }
}
