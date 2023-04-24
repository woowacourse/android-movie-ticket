package woowacourse.movie.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.Ad
import woowacourse.movie.R
import woowacourse.movie.movie.Movie

class MovieRecyclerViewAdapter(
    private val movies: List<Movie>,
    private val ad: Ad,
    private val movieOnItemClicked: (Int) -> Unit,
    private val adOnItemClicked: (Ad) -> Unit,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (ViewType.values()[viewType]) {
            ViewType.MOVIE -> {
                val view = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.item_movie_list, viewGroup, false)
                MovieRecyclerViewHolder(view, movieOnItemClicked)
            }
            ViewType.AD -> {
                val view = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.item_ad_list, viewGroup, false)
                AdViewHolder(view, adOnItemClicked, ad)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = movies[position]
        when (holder.itemViewType) {
            ViewType.MOVIE.ordinal -> (holder as MovieRecyclerViewHolder).bind(item)
            ViewType.AD.ordinal -> (holder as AdViewHolder).bind(ad)
        }
    }

    override fun getItemCount() = movies.size

    override fun getItemViewType(position: Int): Int =
        if ((position + 1) % DIVIDE_ONE_GROUP == 0) ViewType.AD.ordinal else ViewType.MOVIE.ordinal

    companion object {
        private const val DIVIDE_ONE_GROUP = 4
    }
}
