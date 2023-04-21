package woowacourse.movie.presentation.activities.movielist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.presentation.activities.movielist.adapter.type.MovieViewType
import woowacourse.movie.presentation.activities.movielist.adapter.viewholder.MovieViewHolder
import woowacourse.movie.presentation.activities.movielist.adapter.viewholder.NativeAdViewHolder
import woowacourse.movie.presentation.model.movieitem.Ad
import woowacourse.movie.presentation.model.movieitem.Movie

class MovieListAdapter(
    private val ads: List<Ad>,
    private val onBookBtnClick: (Movie) -> Unit,
    private val onAdClick: (Ad) -> Unit,
) : RecyclerView.Adapter<ViewHolder>() {
    private val _movies: MutableList<Movie> = mutableListOf()
    private val onMovieItemClick: (Int) -> Unit = { onBookBtnClick(_movies[getMoviePosition(it)]) }
    private val onAdItemClick: (Int) -> Unit = { onAdClick(ads[getAdPosition(it)]) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when (viewType) {
            MovieViewType.MOVIE -> {
                val movieView = inflate(parent.context, parent, R.layout.item_movie)
                return MovieViewHolder(movieView, onMovieItemClick)
            }

            MovieViewType.AD -> {
                val adView = inflate(parent.context, parent, R.layout.item_native_ad)
                return NativeAdViewHolder(adView, onAdItemClick)
            }
        }
        throw IllegalArgumentException(INVALID_VIEW_TYPE_ERROR_MESSAGE)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(_movies[getMoviePosition(position)])
            is NativeAdViewHolder -> holder.bind(ads[getAdPosition(position)])
        }
    }

    override fun getItemViewType(position: Int): Int = when {
        isMovie(position) -> MovieViewType.MOVIE
        isAd(position) -> MovieViewType.AD
        else -> throw IllegalArgumentException(INVALID_POSITION_ERROR_MESSAGE)
    }

    override fun getItemCount(): Int = _movies.size + adsSize()

    private fun inflate(context: Context, parent: ViewGroup, @LayoutRes resId: Int): View =
        LayoutInflater.from(context).inflate(resId, parent, false)

    fun addAll(newMovies: List<Movie>) {
        _movies.addAll(newMovies)
        notifyItemRangeChanged(_movies.size, newMovies.size)
    }

    private fun adsSize() = _movies.size / ADS_INTERVAL

    private fun isAd(position: Int): Boolean =
        position != 0 && (position + 1) % (ADS_INTERVAL + 1) == 0

    private fun isMovie(position: Int): Boolean = !isAd(position)

    private fun getMoviePosition(position: Int): Int = position - (position / (ADS_INTERVAL + 1))

    private fun getAdPosition(position: Int): Int = (position / (ADS_INTERVAL + 1)) % ads.size

    companion object {
        private const val ADS_INTERVAL = 3
        private const val INVALID_VIEW_TYPE_ERROR_MESSAGE = "올바르지 않은 뷰 유형입니다."
        private const val INVALID_POSITION_ERROR_MESSAGE = "올바르지 않은 순서입니다."
    }
}
