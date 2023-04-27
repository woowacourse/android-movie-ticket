package woowacourse.movie.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import domain.Movie
import domain.Movies
import woowacourse.movie.view.model.AdvertisementUiModel
import woowacourse.movie.view.model.MovieAdapterViewType
import woowacourse.movie.viewholder.AdvertisementItemViewHolder
import woowacourse.movie.viewholder.MovieItemViewHolder

class MovieAdapter(
    private val movies: Movies,
    private val advertisementUiModel: AdvertisementUiModel,
    private val advertisementClickEvent: (AdvertisementUiModel) -> Unit,
    private val movieListClickEvent: (Movie) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (position % CYCLE) {
            ADVERTISEMENT_TURN -> MovieAdapterViewType.ADVERTISEMENT.value
            else -> MovieAdapterViewType.MOVIE.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (MovieAdapterViewType.find(viewType)) {
            MovieAdapterViewType.MOVIE -> MovieItemViewHolder(
                inflateView(parent, R.layout.item_movie)
            )
            MovieAdapterViewType.ADVERTISEMENT -> AdvertisementItemViewHolder(
                inflateView(parent, R.layout.item_advertisement)
            )
        }
    }

    private fun inflateView(viewGroup: ViewGroup, layoutId: Int): View {
        return LayoutInflater.from(viewGroup.context).inflate(layoutId, viewGroup, false)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is AdvertisementItemViewHolder) viewHolder.bind(
            advertisementUiModel, advertisementClickEvent
        )
        if (viewHolder is MovieItemViewHolder) viewHolder.bind(
            movies.value[position], movieListClickEvent
        )
    }

    override fun getItemCount(): Int {
        return 10_000
    }

    companion object {
        private const val ADVERTISEMENT_TURN = 3
        private const val CYCLE = 4
    }
}
