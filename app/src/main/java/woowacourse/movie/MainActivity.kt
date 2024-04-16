package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.model.Movie

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieList =
            listOf(
                Movie(
                    posterImageId = R.drawable.harrypotter_poster,
                    title = this.getString(R.string.harry_potter_title),
                    screeningDate = this.getString(R.string.harry_potter_screening_date),
                    runningTime = this.getString(R.string.harry_potter_running_time).toInt(),
                    summary = this.getString(R.string.harry_potter_summary),
                ),
            )

        val adapter = MovieListAdapter(this, movieList)
        findViewById<ListView>(R.id.movieList).adapter = adapter
    }
}
