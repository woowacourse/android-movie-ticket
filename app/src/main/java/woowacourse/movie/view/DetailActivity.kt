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
import woowacourse.movie.presenter.DetailPresenterImpl


class DetailActivity : AppCompatActivity(), DetailContract.View {
    private lateinit var detailPresenter: DetailContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detailPresenter = DetailPresenterImpl(this)

        setupView()
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

    private fun setupReserveButton() {
        val movie: Movie = intent?.parcelable("movie") ?: Movie(
            poster = R.drawable.poster,
            title = "해리 포터 1",
            date = "2024-04-12",
            runTime = "152분"
        )
        val reserveButton = findViewById<Button>(R.id.reserve)
        val detailPersonCounter = findViewById<TextView>(R.id.detail_person_counter)
        reserveButton.setOnClickListener {
            val ticket = Ticket(
                movie = movie, personCount = detailPersonCounter.text.toString().toInt()
            )
            detailPresenter.onReserveButtonClicked(ticket)
        }
    }

    override fun startTicketActivity(ticket: Ticket) {
        startActivity(Intent(this, TicketActivity::class.java).apply {
            putExtra("ticket", ticket)
        })
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun setupView() {
        val movie: Movie = intent?.parcelable("movie") ?: Movie(
            poster = R.drawable.poster,
            title = "해리 포터 1",
            date = "2024-04-12",
            runTime = "152분"
        )
        val detailInformation = findViewById<TextView>(R.id.detail_information)
        val detailPoster = findViewById<ImageView>(R.id.detail_poster)
        detailInformation.text = movie.information
        detailPoster.setImageResource(movie.poster)

        setupReserveButton()
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
            detailPresenter.onMinusButtonClicked(value)
        }

        plusButton.setOnClickListener {
            val value = detailPersonCounter.text.toString().toInt()
            detailPresenter.onPlusButtonClicked(value)
        }
    }

    override fun updateCounter(count: Int) {
        val detailPersonCounter = findViewById<TextView>(R.id.detail_person_counter)
        detailPersonCounter.text = count.toString()
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
