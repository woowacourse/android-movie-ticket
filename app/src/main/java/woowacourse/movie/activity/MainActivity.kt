package woowacourse.movie.activity

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.presenter.Presenter

class MainActivity : AppCompatActivity() {
    private lateinit var moviesListView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)
        val presenter = Presenter(this)
        moviesListView = findViewById(R.id.movies_list_item)
        moviesListView.adapter = presenter.movieAdapter
    }
}
