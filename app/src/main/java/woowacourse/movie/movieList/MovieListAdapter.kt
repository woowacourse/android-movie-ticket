package woowacourse.movie.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.movieList.ItemViewType.ITEM_VIEW_TYPE_AD
import woowacourse.movie.movieList.ItemViewType.ITEM_VIEW_TYPE_MOVIE
import woowacourse.movie.uimodel.MovieModelUi
import woowacourse.movie.viewholder.AdViewHolder
import woowacourse.movie.viewholder.MovieViewHolder

class MovieListAdapter(
    private val movieModelUi: List<MovieModelUi>,
    private val onReservationClickListener: (MovieModelUi.MovieScheduleUi) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::layoutInflater.isInitialized) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        when (ItemViewType.of(viewType)) {
            ITEM_VIEW_TYPE_MOVIE -> {
                return MovieViewHolder(
                    view = layoutInflater.inflate(
                        R.layout.item_movie_list,
                        parent,
                        false,
                    ),
                    onReservationClickListener,
                )
            }
            ITEM_VIEW_TYPE_AD -> {
                return AdViewHolder(
                    view = layoutInflater.inflate(
                        R.layout.item_ad_list,
                        parent,
                        false,
                    ),
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return movieModelUi.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (movieModelUi[position]) {
            is MovieModelUi.MovieScheduleUi -> ITEM_VIEW_TYPE_MOVIE.value
            is MovieModelUi.AdUi -> ITEM_VIEW_TYPE_AD.value
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (ItemViewType.of(getItemViewType(position))) {
            ITEM_VIEW_TYPE_MOVIE -> {
                val item = movieModelUi[position] as MovieModelUi.MovieScheduleUi
                (holder as MovieViewHolder).bind(item)
            }
            ITEM_VIEW_TYPE_AD -> {
                val item = movieModelUi[position] as MovieModelUi.AdUi
                (holder as AdViewHolder).bind(item)
            }
        }
    }
}
