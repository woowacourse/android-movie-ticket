package woowacourse.movie

import android.os.Bundle
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.model.MovieItem

class MainActivity : AppCompatActivity() {
    // TODO 1) MainActivity -> ScreeningMovieActivity
    // TODO 2) DetailMovieActivity -> MovieReservationActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = emptyList<MovieItem>()
        val adapter = MovieAdapter(this, items)
        val listView = findViewById<ListView>(R.id.list_view)
        listView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}
