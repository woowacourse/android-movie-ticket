package woowacourse.movie.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import model.MovieAdItem
import model.MovieListItem
import model.MovieListType
import woowacourse.movie.R
import woowacourse.movie.movieList.viewHolder.AdViewHolder
import woowacourse.movie.movieList.viewHolder.MovieViewHolder

class MovieListAdapter(
    private val items: List<MovieListType>,
    private val onClickButton: (MovieListItem) -> Unit,
    private val onAdClick: (MovieAdItem) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_MOVIE) {
            MovieViewHolder(
                layoutInflater.inflate(R.layout.item_movie_movie_list, parent, false),
            )
        } else {
            AdViewHolder(
                layoutInflater.inflate(R.layout.item_ad_movie_list, parent, false),
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(items[position] as MovieListItem, onClickButton)
            is AdViewHolder -> holder.bind(items[position] as MovieAdItem, onAdClick)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = when (items[position]) {
        is MovieListItem -> TYPE_MOVIE
        is MovieAdItem -> TYPE_AD
    }

    companion object {
        private const val TYPE_MOVIE = 1
        private const val TYPE_AD = 2
    }
}
