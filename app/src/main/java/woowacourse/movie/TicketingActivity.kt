package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.model.Movie
import woowacourse.movie.presenter.TicketingPresenter
import woowacourse.movie.presenter.contract.TicketingContract

class TicketingActivity : AppCompatActivity(), TicketingContract {
    private val countText by lazy { findViewById<TextView>(R.id.tv_count) }
    private lateinit var ticketingPresenter: TicketingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, -1)
        ticketingPresenter = TicketingPresenter(this, movieId)
        ticketingPresenter.assignInitialView()

        val minusButton = findViewById<Button>(R.id.btn_minus)
        val plusButton = findViewById<Button>(R.id.btn_plus)
        val completeButton = findViewById<Button>(R.id.btn_complete)

        minusButton.setOnClickListener {
            ticketingPresenter.decreaseCount()
        }

        plusButton.setOnClickListener {
            ticketingPresenter.increaseCount()
        }

        completeButton.setOnClickListener {
            ticketingPresenter.navigate()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun assignInitialView(
        movie: Movie,
        count: Int,
    ) {
        updateCount(count)
        findViewById<ImageView>(R.id.iv_thumbnail).apply { setImageResource(movie.thumbnail) }
        findViewById<TextView>(R.id.tv_title).apply { text = movie.title }
        findViewById<TextView>(R.id.tv_date).apply {
            text = getString(R.string.title_date, movie.date)
        }
        findViewById<TextView>(R.id.tv_running_time).apply {
            text = getString(R.string.title_running_time, movie.runningTime)
        }
        findViewById<TextView>(R.id.tv_introduction).apply { text = movie.introduction }
    }

    override fun updateCount(count: Int) {
        countText.text = count.toString()
    }

    override fun navigate(
        movieId: Int,
        count: Int,
    ) {
        Intent(this, TicketingResultActivity::class.java).apply {
            putExtra(EXTRA_MOVIE_ID, movieId)
            putExtra(EXTRA_NUMBER_OF_PEOPLE, count)
            startActivity(this)
            finish()
        }
    }

    companion object {
        const val EXTRA_MOVIE_ID = "movie_id"
        const val EXTRA_NUMBER_OF_PEOPLE = "number_of_people"
    }
}
