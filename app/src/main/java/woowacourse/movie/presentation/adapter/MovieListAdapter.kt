package woowacourse.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.domain.admodel.Ad
import woowacourse.movie.presentation.adapter.diffutil.MovieDiffCallback
import woowacourse.movie.presentation.contract.MainContract
import woowacourse.movie.presentation.uimodel.MovieUiModel

class MovieListAdapter(
    private var movieList: List<MovieUiModel>,
    private var adList: List<Ad>,
    private val listener: MainContract.ViewActions,
    private var adExposureCount: Int = DEFAULT_AD_INTERVAL_COUNT,
) : RecyclerView.Adapter<ViewHolder>() {
    fun updateMovieList(newMovieList: List<MovieUiModel>) {
        val diffCallback = MovieDiffCallback(movieList, newMovieList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        movieList = newMovieList
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateAdsList(ads: List<Ad>) {
        adList = ads
    }

    fun setAdExposureCount(exposureCount: Int) {
        adExposureCount = exposureCount
    }

    private fun onReserveButtonClicked(position: Int) {
        listener.reserveMovie(movieList[position].movieId)
    }

    private fun onAdClicked(position: Int) {
        listener.showAdContent(adList[getAdIndex(position)].content)
    }

    override fun getItemViewType(position: Int): Int {
        return ListItemType.getTypeFromPosition(position, adExposureCount)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        return when (viewType) {
            ListItemType.MOVIE_TYPE.code -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
                MovieViewHolder(view, ::onReserveButtonClicked)
            }

            ListItemType.AD_TYPE.code -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.ad_item, parent, false)
                AdViewHolder(view, ::onAdClicked)
            }

            else -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
                MovieViewHolder(view, ::onReserveButtonClicked)
            }
        }
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is MovieViewHolder -> holder.bind(movieList[position])
            is AdViewHolder -> holder.bind(adList[getAdIndex(position)].imageId)
        }
    }

    override fun getItemCount(): Int = movieList.size

    private fun getAdIndex(listIndex: Int): Int {
        return (listIndex / adExposureCount) % adList.size
    }

    companion object {
        const val DEFAULT_AD_INTERVAL_COUNT = 4
    }
}
