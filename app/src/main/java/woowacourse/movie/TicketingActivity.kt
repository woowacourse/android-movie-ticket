package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import woowacourse.movie.model.Movie
import woowacourse.movie.presenter.TicketingPresenter
import woowacourse.movie.presenter.contract.TicketingContract

class TicketingActivity : AppCompatActivity(), TicketingContract.View, OnItemSelectedListener {
    private val countText by lazy { findViewById<TextView>(R.id.tv_count) }
    private lateinit var ticketingPresenter: TicketingPresenter
    private val movieId by lazy { intent.getLongExtra(EXTRA_MOVIE_ID, EXTRA_DEFAULT_MOVIE_ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ticketingPresenter = TicketingPresenter(this, movieId, DEFAULT_COUNT)
        ticketingPresenter.initializeTicketingData()
        initializeButtons()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNT, ticketingPresenter.countValue)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let {
            val count = it.getInt(KEY_COUNT, DEFAULT_COUNT)
            ticketingPresenter = TicketingPresenter(this, movieId, count)
            countText.text = ticketingPresenter.countValue.toString()
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
        findViewById<ImageView>(R.id.iv_thumbnail).apply { setImageResource(movie.thumbnailResourceId) }
        findViewById<TextView>(R.id.tv_title).apply { text = movie.title }
        findViewById<TextView>(R.id.tv_date).apply {
            text =
                getString(R.string.title_date, movie.startDate.toString(), movie.endDate.toString())
        }
        findViewById<TextView>(R.id.tv_running_time).apply {
            text = getString(R.string.title_running_time, movie.runningTime)
        }
        findViewById<TextView>(R.id.tv_introduction).apply { text = movie.introduction }

        findViewById<Spinner>(R.id.spinner_date).apply {
            adapter =
                ArrayAdapter(
                    this@TicketingActivity,
                    android.R.layout.simple_spinner_item,
                    movie.dates,
                )
            onItemSelectedListener = this@TicketingActivity
        }

        findViewById<Spinner>(R.id.spinner_time).apply {
            adapter =
                ArrayAdapter(
                    this@TicketingActivity,
                    android.R.layout.simple_spinner_item,
                    movie.times,
                )
            onItemSelectedListener = this@TicketingActivity
        }
    }

    override fun updateCount(count: Int) {
        countText.text = count.toString()
    }

    override fun navigateToTicketingResult(
        movieId: Long,
        count: Int,
        totalPrice: Int,
        date: String,
        time: String,
    ) {
        Intent(this, TicketingResultActivity::class.java).apply {
            putExtra(EXTRA_MOVIE_ID, movieId)
            putExtra(EXTRA_COUNT, count)
            putExtra(EXTRA_TOTAL_PRICE, totalPrice)
            putExtra(EXTRA_DATE, date)
            putExtra(EXTRA_TIME, time)
            startActivity(this)
            finish()
        }
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun initializeButtons() {
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
            ticketingPresenter.reserveTickets()
        }
    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long,
    ) {
        when (parent?.id) {
            R.id.spinner_date ->
                ticketingPresenter.updateDate(parent.getItemAtPosition(position).toString())
            R.id.spinner_time ->
                ticketingPresenter.updateTime(parent.getItemAtPosition(position).toString())
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        when (parent?.id) {
            R.id.spinner_date ->
                ticketingPresenter.updateDate(parent.getItemAtPosition(0).toString())
            R.id.spinner_time ->
                ticketingPresenter.updateTime(parent.getItemAtPosition(0).toString())
        }
    }

    companion object {
        const val EXTRA_MOVIE_ID = "movie_id"
        const val EXTRA_COUNT = "number_of_people"
        const val EXTRA_TOTAL_PRICE = "total_price"
        const val EXTRA_DATE = "movie_date"
        const val EXTRA_TIME = "movie_time"
        const val EXTRA_DEFAULT_MOVIE_ID = -1L
        private const val DEFAULT_COUNT = 1
        private const val KEY_COUNT = "count"
    }
}
