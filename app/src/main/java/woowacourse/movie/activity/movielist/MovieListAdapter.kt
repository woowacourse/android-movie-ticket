package woowacourse.movie.activity.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieModel

class MovieListAdapter(
    private val adInterval: Int,
    private val movies: List<MovieModel>,
    private val onClick: (MovieModel) -> Unit,
) :
    RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return when (ListViewType.values()[viewType]) {
            ListViewType.AD_VIEWTYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.ad_item, parent, false)
                CustomViewHolder.AdItemViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
                CustomViewHolder.MovieItemViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        when (holder) {
            is CustomViewHolder.AdItemViewHolder -> { // ad item
                holder.set(R.drawable.ad)
            }
            is CustomViewHolder.MovieItemViewHolder -> { // movie item
                holder.set(movies[position - ((position + 1) / (adInterval + 1))]) {
                    onClick(movies[position - ((position + 1) / (adInterval + 1))])
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return ListViewType.getViewType(adInterval, position).ordinal
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = movies.size
}
