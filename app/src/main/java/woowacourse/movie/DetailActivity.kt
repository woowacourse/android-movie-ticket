package woowacourse.movie

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupView()
        setupActionBar()
        setupCounter()
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
            startActivity(Intent(this, TicketActivity::class.java).apply {
                putExtra("ticket", ticket)
            })
            finish()
        }
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
            if (value > 1) {
                detailPersonCounter.text = (value - 1).toString()
            }
        }

        plusButton.setOnClickListener {
            val value = detailPersonCounter.text.toString().toInt() + 1
            detailPersonCounter.text = value.toString()
        }
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
