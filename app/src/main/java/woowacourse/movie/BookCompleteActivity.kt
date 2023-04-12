package woowacourse.movie

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BookCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_complete)

        val movieData = intent.customGetSerializable<Movie>("movieData")
        val ticketCount = intent.getIntExtra("ticketCount", 0)

        findViewById<TextView>(R.id.tv_book_movie_title).text = movieData?.title
        findViewById<TextView>(R.id.tv_book_date).text = movieData?.releaseDate
        findViewById<TextView>(R.id.tv_book_person_count).text =
            getString(R.string.book_person_count).format(ticketCount)
        findViewById<TextView>(R.id.tv_book_total_pay).text =
            getString(R.string.book_total_pay).format(ticketCount * 13000)
    }
}
