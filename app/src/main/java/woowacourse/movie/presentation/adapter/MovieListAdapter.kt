package woowacourse.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.presentation.adapter.diffutil.MovieDiffCallback
import woowacourse.movie.presentation.contract.MainContract
import woowacourse.movie.presentation.uimodel.MovieUiModel

class MovieListAdapter(
    private var movieList: List<MovieUiModel>,
    private val listener: MainContract.ViewActions,
) : RecyclerView.Adapter<MovieViewHolder>() {
    fun updateMovieList(newMovieList: List<MovieUiModel>) {
        val diffCallback = MovieDiffCallback(movieList, newMovieList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        movieList = newMovieList
        diffResult.dispatchUpdatesTo(this)
    }

    private fun onReserveButtonClicked(position: Int) {
        listener.reserveMovie(movieList[position].movieId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view, ::onReserveButtonClicked)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size
}
