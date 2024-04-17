package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.MovieListActivity.Companion.EXTRA_MOVIE_ID

class TicketingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, -1)
        val movie = MOVIES.find { it.id == movieId }
        movie?.let {
            findViewById<ImageView>(R.id.iv_thumbnail).apply { }
            findViewById<TextView>(R.id.tv_title).apply { text = it.title }
            findViewById<TextView>(R.id.tv_date).apply { text = "$text ${it.date}" }
            findViewById<TextView>(R.id.tv_running_time).apply { text = "$text ${it.runningTime}" }
            findViewById<TextView>(R.id.tv_introduction).apply { text = it.introduction }
        }

        var count = 1
        val minusButton = findViewById<Button>(R.id.btn_minus)
        val plusButton = findViewById<Button>(R.id.btn_plus)
        val countText = findViewById<TextView>(R.id.tv_count).apply { text = count.toString() }
        val completeButton = findViewById<Button>(R.id.btn_complete)

        minusButton.setOnClickListener {
            if (count > 1) {
                count--
                countText.text = count.toString()
            }
        }

        plusButton.setOnClickListener {
            count++
            countText.text = count.toString()
        }

        completeButton.setOnClickListener {
            Intent(this, TicketingResultActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_TITLE, movie?.title)
                putExtra(EXTRA_MOVIE_DATE, movie?.date)
                putExtra(EXTRA_NUMBER_OF_PEOPLE, count)
                startActivity(this)
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_MOVIE_TITLE = "movie_title"
        const val EXTRA_MOVIE_DATE = "movie_date"
        const val EXTRA_NUMBER_OF_PEOPLE = "number_of_people"
    }
}
