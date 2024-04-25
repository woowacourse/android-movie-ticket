package woowacourse.movie.view

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket
import woowacourse.movie.presenter.DetailContract
import woowacourse.movie.presenter.DetailPresenter


class DetailActivity : AppCompatActivity(), DetailContract.View {
    private lateinit var detailPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        detailPresenter = DetailPresenter(this)
        detailPresenter.loadMovie()

        setupActionBar()
        setupCounter()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val count = findViewById<TextView>(R.id.detail_person_counter).text.toString().toInt()
        outState.putInt("count", count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let {
            val count = it.getInt("count")
            val countTextView = findViewById<TextView>(R.id.detail_person_counter)
            countTextView.text = count.toString()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun setupReserveButton(movie: Movie) {
        val reserveButton = findViewById<Button>(R.id.reserve)
        val detailPersonCounter = findViewById<TextView>(R.id.detail_person_counter)
        val personCounter = detailPersonCounter.text.toString().toInt()
        reserveButton.setOnClickListener {
            detailPresenter.onClickedReservation(movie, personCounter)
        }
    }

    private fun setupView(movie: Movie) {
        val movie: Movie = intent?.parcelable("movie") ?: movie
        val detailInformation = findViewById<TextView>(R.id.detail_information)
        val detailPoster = findViewById<ImageView>(R.id.detail_poster)
        detailInformation.text = movie.information
        detailPoster.setImageResource(movie.poster)

        setupReserveButton(movie)
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun setupCounter() {
        val minusButton = findViewById<Button>(R.id.button_minus)
        val plusButton = findViewById<Button>(R.id.button_plus)
        val detailPersonCounter = findViewById<TextView>(R.id.detail_person_counter)

        minusButton.setOnClickListener {
            val value = detailPersonCounter.text.toString().toInt()
            detailPresenter.onClickedMinusCount(value)
        }

        plusButton.setOnClickListener {
            val value = detailPersonCounter.text.toString().toInt()
            detailPresenter.onClickedPlusButton(value)
        }
    }

    override fun showMovie(movie: Movie) {
        setupView(movie)
    }

    override fun updateCount(count: Int) {
        val detailPersonCounter = findViewById<TextView>(R.id.detail_person_counter)
        detailPersonCounter.text = count.toString()
    }

    override fun showTicket(ticket: Ticket) {
        startActivity(Intent(this, TicketActivity::class.java).apply {
            putExtra("ticket", ticket)
        })
        finish()
    }
}

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}
