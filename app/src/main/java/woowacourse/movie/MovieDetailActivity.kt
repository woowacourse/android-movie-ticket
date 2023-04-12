package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MovieDetailActivity : BackButtonActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieData = intent.customGetSerializable<Movie>("movieData")

        findViewById<ImageView>(R.id.iv_movie_poster).setImageResource(
            movieData?.poster ?: R.drawable.ic_launcher_background
        )
        findViewById<TextView>(R.id.tv_movie_title).text = movieData?.title
        findViewById<TextView>(R.id.tv_movie_release_date).text = movieData?.releaseDate
        findViewById<TextView>(R.id.tv_movie_running_time).text = movieData?.runningTime
        findViewById<TextView>(R.id.tv_movie_synopsis).text = movieData?.synopsis

        setClickListener(movieData)
    }

    private fun setClickListener(movieData: Movie?) {
        val personCountTextView = findViewById<TextView>(R.id.tv_ticket_count)
        var currentCount = personCountTextView.text.toString().toInt()

        findViewById<Button>(R.id.bt_ticket_count_minus).setOnClickListener {
            if (personCountTextView.text == "1") {
                Toast.makeText(this, "1장 이상의 표를 선택해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            currentCount--
            personCountTextView.text = currentCount.toString()
        }

        findViewById<Button>(R.id.bt_ticket_count_plus).setOnClickListener {
            currentCount++
            personCountTextView.text = currentCount.toString()
        }

        findViewById<Button>(R.id.bt_book_complete).setOnClickListener {
            val intent = Intent(this, BookCompleteActivity::class.java).apply {
                putExtra("movieData", movieData)
                putExtra("ticketCount", currentCount)
            }
            startActivity(intent)
        }
    }
}
