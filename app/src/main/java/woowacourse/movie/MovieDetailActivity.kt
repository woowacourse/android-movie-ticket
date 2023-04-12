package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.Date
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.PeopleCount

class MovieDetailActivity : AppCompatActivity() {
    private var peopleCount = PeopleCount()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie = getMovieFromIntent()
        setMovieInfo(movie)
        setPeopleCountController()
    }

    private fun getMovieFromIntent() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra("movie", Movie::class.java)
    } else {
        intent.getSerializableExtra("movie")
    } as Movie

    private fun setMovieInfo(movie: Movie) {
        val moviePoster = findViewById<ImageView>(R.id.detail_poster)
        val movieTitle = findViewById<TextView>(R.id.detail_title)
        val movieDate = findViewById<TextView>(R.id.detail_date)
        val movieTime = findViewById<TextView>(R.id.detail_running_time)
        val movieDescription = findViewById<TextView>(R.id.detail_description)

        moviePoster.setImageResource(movie.poster)
        movieTitle.text = movie.title
        movieDate.text = movie.date.toScreenDate()
        movieTime.text = movie.time.toRunningTime()
        movieDescription.text = movie.description
    }

    private fun Date.toScreenDate(): String = "상영일: $year.$month.$day"

    private fun Int.toRunningTime(): String = "러닝타임: ${this}분"

    private fun setPeopleCountController() {
        val peopleCountView = findViewById<TextView>(R.id.detail_people_count)
        setPeopleCountView(peopleCountView)
        setMinusButton(peopleCountView)
        setPlusButton(peopleCountView)
    }

    private fun setPeopleCountView(peopleCountView: TextView) {
        peopleCountView.text = "${peopleCount.count}"
    }

    private fun setMinusButton(peopleCountView: TextView) {
        val minusButton = findViewById<Button>(R.id.detail_minus_button)
        minusButton.setOnClickListener {
            peopleCount = peopleCount.minusCount()
            setPeopleCountView(peopleCountView)
        }
    }

    private fun setPlusButton(peopleCountView: TextView) {
        val plusButton = findViewById<Button>(R.id.detail_plus_button)
        plusButton.setOnClickListener {
            peopleCount = peopleCount.plusCount()
            setPeopleCountView(peopleCountView)
        }
    }
}
