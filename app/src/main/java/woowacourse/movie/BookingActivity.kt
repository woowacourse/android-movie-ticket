package woowacourse.movie

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_POSTER
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_RELEASE_DATE
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_RUNNING_TIME
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_TITLE

class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        initView()
    }

    private fun initView() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movieTitleView = findViewById<TextView>(R.id.tv_title)
        movieTitleView.text = intent.getStringExtra(KEY_MOVIE_TITLE)

        val moviePosterView = findViewById<ImageView>(R.id.img_movie_poster)
        moviePosterView.setImageResource(intent.getIntExtra(KEY_MOVIE_POSTER, 0))

        val movieRunningTimeView = findViewById<TextView>(R.id.tv_screening_period)
        movieRunningTimeView.text = intent.getStringExtra(KEY_MOVIE_RELEASE_DATE)

        val movieReleaseTimeView = findViewById<TextView>(R.id.tv_running_time)
        movieReleaseTimeView.text = intent.getStringExtra(KEY_MOVIE_RUNNING_TIME)
    }
}
