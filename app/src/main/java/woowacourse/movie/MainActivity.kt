package woowacourse.movie

import android.widget.ListView
import woowacourse.movie.model.Movie

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_main
    
    override fun onCreateSetup() {
        val movieList = createMovieList()
        val adapter = MovieListAdapter(this, movieList)
        findViewById<ListView>(R.id.movieList).adapter = adapter
    }
    
    private fun createMovieList(): List<Movie> {
        return listOf(
            Movie(
                posterImageId = R.drawable.harrypotter_poster,
                title = this.getString(R.string.harry_potter_title),
                screeningDate = this.getString(R.string.harry_potter_screening_date),
                runningTime = this.getString(R.string.harry_potter_running_time).toInt(),
                summary = this.getString(R.string.harry_potter_summary),
            ),
        )
    }
}
