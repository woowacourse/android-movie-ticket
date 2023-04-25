package woowacourse.movie.presentation.view.main

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.databinding.ItemMovieListBinding
import woowacourse.movie.model.Movie
import woowacourse.movie.presentation.view.moviedetail.MovieDetailActivity

class MovieListViewHolder(binding: ItemMovieListBinding) : RecyclerView.ViewHolder(binding.root) {
    private val context = binding.root.context
    private val ivMoviePoster = binding.ivMoviePoster
    private val tvMovieTitle = binding.tvMovieTitle
    private val tvMovieReleaseDate = binding.tvMovieReleaseDate
    private val tvMovieRunningTime = binding.tvMovieRunningTime
    private val btBookNow = binding.btBookNow
    fun bind(movie: Movie) {
        ivMoviePoster.setImageResource(movie.poster)
        tvMovieTitle.text = movie.title
        tvMovieReleaseDate.text = movie.releaseDate
        tvMovieRunningTime.text = movie.runningTime
        btBookNow.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MovieDetailActivity.MOVIE_DATA_INTENT_KEY, movie)
            context.startActivity(intent)
        }
    }
}
