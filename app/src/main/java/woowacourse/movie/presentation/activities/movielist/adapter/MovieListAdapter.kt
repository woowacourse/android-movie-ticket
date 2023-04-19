package woowacourse.movie.presentation.activities.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.presentation.activities.movielist.adapter.type.MovieViewType
import woowacourse.movie.presentation.activities.movielist.adapter.viewholder.MovieViewHolder
import woowacourse.movie.presentation.activities.movielist.adapter.viewholder.NativeAdViewHolder
import woowacourse.movie.presentation.model.movieitem.Ad
import woowacourse.movie.presentation.model.movieitem.Movie

class MovieListAdapter(
    private val movies: List<Movie>,
    private val ads: List<Ad>,
    private val onBookBtnClick: (Movie) -> Unit,
    private val onAdClick: (Ad) -> Unit,
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when (viewType) {
            MovieViewType.MOVIE -> {
                val movieView =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
                return MovieViewHolder(movieView, onBookBtnClick)
            }

            MovieViewType.AD -> {
                val adView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_native_ads, parent, false)
                return NativeAdViewHolder(adView, onAdClick)
            }
        }
        throw IllegalArgumentException(INVALID_VIEW_TYPE_ERROR_MESSAGE)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(movies[getMoviePosition(position)])
            is NativeAdViewHolder -> holder.bind(ads[getAdPosition(position)])
        }
    }

    override fun getItemViewType(position: Int): Int = when {
        isMovie(position) -> MovieViewType.MOVIE
        isAd(position) -> MovieViewType.AD
        else -> throw IllegalArgumentException(INVALID_POSITION_ERROR_MESSAGE)
    }

    override fun getItemCount(): Int = movies.size + ads.size

    private fun isAd(position: Int): Boolean =
        position != 0 && (position + 1) % (ADS_INTERVAL + 1) == 0

    private fun isMovie(position: Int): Boolean = !isAd(position)

    private fun getMoviePosition(position: Int): Int = position - (position / ADS_INTERVAL)

    private fun getAdPosition(position: Int): Int = position / ADS_INTERVAL

    companion object {
        private const val ADS_INTERVAL = 3
        private const val INVALID_VIEW_TYPE_ERROR_MESSAGE = "올바르지 않은 뷰 유형입니다."
        private const val INVALID_POSITION_ERROR_MESSAGE = "올바르지 않은 순서입니다."
    }
}
