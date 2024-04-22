package woowacourse.movie.ui.home

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.ui.home.adapter.MovieContentListAdapter

class MovieHomeActivity : AppCompatActivity(), MovieHomeContract.View {
    private val movieContentList: ListView by lazy { findViewById(R.id.movie_content_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_home)

        setUpMovieContentListAdapter()
    }

    override fun setUpMovieContentListAdapter() {
        movieContentList.adapter = MovieContentListAdapter(this, MovieContentsImpl.findAll())
    }
}
