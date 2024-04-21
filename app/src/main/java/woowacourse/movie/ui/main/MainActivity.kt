package woowacourse.movie.ui.main

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.ui.main.adapter.MovieContentListAdapter

class MainActivity : AppCompatActivity(), MainContract.View {
    private val movieContentList: ListView by lazy { findViewById(R.id.movie_content_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpMovieContentListAdapter()
    }

    override fun setUpMovieContentListAdapter() {
        movieContentList.adapter = MovieContentListAdapter(this, MovieContentsImpl.findAll())
    }
}
