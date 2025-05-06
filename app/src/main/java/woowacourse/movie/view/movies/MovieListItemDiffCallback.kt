package woowacourse.movie.view.movies

import androidx.recyclerview.widget.DiffUtil

class MovieListItemDiffCallback : DiffUtil.ItemCallback<MovieListItem>() {
    override fun areItemsTheSame(
        oldItem: MovieListItem,
        newItem: MovieListItem,
    ): Boolean =
        when {
            oldItem is MovieListItem.MovieItem && newItem is MovieListItem.MovieItem ->
                oldItem.movie == newItem.movie
            oldItem is MovieListItem.AdItem && newItem is MovieListItem.AdItem ->
                oldItem.ad.id == newItem.ad.id
            else -> false
        }

    override fun areContentsTheSame(
        oldItem: MovieListItem,
        newItem: MovieListItem,
    ): Boolean = oldItem == newItem
}
