package woowacourse.movie.presentation.view.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.presentation.model.MovieUiModel

class MoviesAdapter(
    private val eventListener: OnMovieEventListener,
) : ListAdapter<MovieUiModel, RecyclerView.ViewHolder>(MoviesDiffUtil) {
    override fun getItemViewType(position: Int): Int = if (isAdPosition(position)) AD_VIEW_TYPE else MOVIE_VIEW_TYPE

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            MOVIE_VIEW_TYPE ->
                MovieViewHolder(
                    inflater.inflate(R.layout.item_movie, parent, false),
                    eventListener,
                )
            AD_VIEW_TYPE ->
                AdViewHolder(
                    inflater.inflate(R.layout.item_ad, parent, false),
                )
            else -> error(INVALID_UNKNOWN_VIEW_TYPE.format(viewType))
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is MovieViewHolder -> getMovieForPosition(position)?.let(holder::bind)
            is AdViewHolder -> holder.bind(R.drawable.woowacourse_ad)
        }
    }

    override fun getItemCount(): Int = currentList.size + getAdCount()

    private fun isAdPosition(position: Int): Boolean = (position + 1) % AD_FREQUENCY == 0

    private fun getMovieForPosition(position: Int): MovieUiModel? {
        val realPosition = position - (position / AD_FREQUENCY)
        return currentList.getOrNull(realPosition)
    }

    private fun getAdCount(): Int = currentList.size / (AD_FREQUENCY - 1)

    companion object {
        private const val MOVIE_VIEW_TYPE = 1
        private const val AD_VIEW_TYPE = 2
        private const val AD_FREQUENCY = 4
        private const val INVALID_UNKNOWN_VIEW_TYPE = "Unknown ViewType: %s"
    }
}
