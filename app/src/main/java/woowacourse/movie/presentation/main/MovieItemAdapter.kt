package woowacourse.movie.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.presentation.main.viewholder.AdViewHolder
import woowacourse.movie.presentation.main.viewholder.MovieViewHolder

class MovieItemAdapter(
    private val movieItems: List<MovieItem>,
    private val clickBook: (Long) -> Unit,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.movie_list_item -> MovieViewHolder(view, clickBook)
            R.layout.movie_list_ad_item -> AdViewHolder(view)
            else -> throw NoSuchElementException()
        }
    }

    override fun getItemCount(): Int = movieItems.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val item = movieItems[position]
        when (viewHolder) {
            is MovieViewHolder -> viewHolder.bind(item as MovieItem.Movie)
            is AdViewHolder -> viewHolder.bind(item as MovieItem.Ad)
        }
    }

    override fun getItemViewType(position: Int) = when (movieItems[position]) {
        is MovieItem.Movie -> R.layout.movie_list_item
        is MovieItem.Ad -> R.layout.movie_list_ad_item
        else -> throw NoSuchElementException()
    }
}
