package woowacourse.movie.feature.movies.view.adapter

import androidx.recyclerview.widget.DiffUtil

object DiffCallback : DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(
        oldMovieItem: MovieItem,
        newMovieItem: MovieItem,
    ): Boolean = oldMovieItem.id == newMovieItem.id && oldMovieItem::class == newMovieItem::class

    override fun areContentsTheSame(
        oldMovieItem: MovieItem,
        newMovieItem: MovieItem,
    ): Boolean = oldMovieItem == newMovieItem
}
