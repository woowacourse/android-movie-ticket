package woowacourse.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.presentation.model.AdModel
import woowacourse.movie.presentation.model.MovieItemModel
import woowacourse.movie.presentation.model.MovieModel
import woowacourse.movie.presentation.viewholder.AdViewHolder
import woowacourse.movie.presentation.viewholder.MovieViewHolder

class MovieItemAdapter(
    private val movieItems: List<MovieItemModel>,
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
            is MovieViewHolder -> viewHolder.bind(item as MovieModel)
            is AdViewHolder -> viewHolder.bind(item as AdModel)
        }
    }

    override fun getItemViewType(position: Int) = when (movieItems[position]) {
        is MovieModel -> R.layout.movie_list_item
        is AdModel -> R.layout.movie_list_ad_item
        else -> throw NoSuchElementException()
    }
}
