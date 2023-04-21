package woowacourse.movie.movieList

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieModelUi

class MovieListAdapter(
    private val movieModelUi: List<MovieModelUi>,
    private val onReservationClickListener: (MovieModelUi.MovieScheduleUi) -> Unit,
) : BaseAdapter() {

    override fun getCount(): Int {
        return movieModelUi.size
    }

    override fun getItem(position: Int): MovieModelUi {
        return movieModelUi[position]
    }

    override fun getItemViewType(position: Int): Int {
        return when (movieModelUi[position]) {
            is MovieModelUi.MovieScheduleUi -> ITEM_VIEW_TYPE_MOVIE
            is MovieModelUi.AdUi -> ITEM_VIEW_TYPE_AD
        }
    }

    override fun getViewTypeCount(): Int {
        return ITEM_VIEW_TYPE_MAX
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        Log.d("krrong", "${convertView == null}")
        return when (getItemViewType(position)) {
            ITEM_VIEW_TYPE_MOVIE -> {
                val movieViewHolder: MovieViewHolder
                if (convertView != null) {
                    convertView.also {
                        movieViewHolder = it.tag as MovieViewHolder
                        movieViewHolder.bind(movieModelUi[position] as MovieModelUi.MovieScheduleUi, onReservationClickListener)
                    }
                } else {
                    val v = View.inflate(
                        parent?.context,
                        R.layout.item_movie_list,
                        null,
                    )
                    movieViewHolder = MovieViewHolder(v)
                    movieViewHolder.bind(movieModelUi[position] as MovieModelUi.MovieScheduleUi, onReservationClickListener)
                    v.tag = movieViewHolder
                    v
                }
            }
            ITEM_VIEW_TYPE_AD -> {
                val adViewHolder: AdViewHolder
                if (convertView != null) {
                    convertView.also { adViewHolder = it.tag as AdViewHolder }
                } else {
                    val v = View.inflate(
                        parent?.context,
                        R.layout.item_ad_list,
                        null,
                    )
                    adViewHolder = AdViewHolder(v)
                    adViewHolder.bind(movieModelUi[position] as MovieModelUi.AdUi)
                    v.tag = adViewHolder
                    v
                }
            }
            else -> {
                val v = View.inflate(
                    parent?.context,
                    R.layout.item_ad_list,
                    null,
                )
                v
            }
        }
    }

    companion object {
        private const val ITEM_VIEW_TYPE_MOVIE = 0
        private const val ITEM_VIEW_TYPE_AD = 1
        private const val ITEM_VIEW_TYPE_MAX = 2
    }
}
