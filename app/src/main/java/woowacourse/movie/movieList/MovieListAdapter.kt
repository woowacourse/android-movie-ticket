package woowacourse.movie.movieList

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.movieList.ItemViewType.Companion.ITEM_VIEW_TYPE_MAX
import woowacourse.movie.movieList.ItemViewType.ITEM_VIEW_TYPE_AD
import woowacourse.movie.movieList.ItemViewType.ITEM_VIEW_TYPE_MOVIE
import woowacourse.movie.uimodel.MovieModelUi

class MovieListAdapter(
    private val movieModelUi: List<MovieModelUi>,
    private val onReservationClickListener: (MovieModelUi.MovieScheduleUi) -> Unit,
) : BaseAdapter() {

    private val movieViewHolder: MutableMap<View, MovieViewHolder> = mutableMapOf()
    private val adViewHolder: MutableMap<View, AdViewHolder> = mutableMapOf()

    override fun getCount(): Int {
        return movieModelUi.size
    }

    override fun getItem(position: Int): MovieModelUi {
        return movieModelUi[position]
    }

    override fun getItemViewType(position: Int): Int {
        return when (movieModelUi[position]) {
            is MovieModelUi.MovieScheduleUi -> ITEM_VIEW_TYPE_MOVIE.value
            is MovieModelUi.AdUi -> ITEM_VIEW_TYPE_AD.value
        }
    }

    override fun getViewTypeCount(): Int {
        return ITEM_VIEW_TYPE_MAX
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: initView(position, parent)

        when (ItemViewType.of(getItemViewType(position))) {
            ITEM_VIEW_TYPE_MOVIE -> movieViewHolder.getOrPut(view) { MovieViewHolder(view) }
                .bind(movieModelUi[position] as MovieModelUi.MovieScheduleUi, onReservationClickListener)
            ITEM_VIEW_TYPE_AD -> adViewHolder.getOrPut(view) { AdViewHolder(view) }
                .bind(movieModelUi[position] as MovieModelUi.AdUi)
        }

        return view
    }

    private fun initView(position: Int, parent: ViewGroup?): View {
        return when (ItemViewType.of(getItemViewType(position))) {
            ITEM_VIEW_TYPE_MOVIE -> View.inflate(parent?.context, R.layout.item_movie_list, null)
            ITEM_VIEW_TYPE_AD -> View.inflate(parent?.context, R.layout.item_ad_list, null)
        }
    }
}
