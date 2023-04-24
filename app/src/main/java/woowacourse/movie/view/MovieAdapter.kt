package woowacourse.movie.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import domain.Movie
import domain.Movies
import woowacourse.movie.view.model.AdvertisementUiModel
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
            ADVERTISEMENT_TURN -> ADVERTISEMENT
            else -> MOVIE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == MOVIE) {
            val movieView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
            return MovieItemViewHolder(movieView)
        }
        val advertisementView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_advertisement, parent, false)
        return AdvertisementItemViewHolder(advertisementView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is AdvertisementItemViewHolder) viewHolder.bind(
            advertisementUiModel,
            advertisementClickEvent
        )
        if (viewHolder is MovieItemViewHolder) viewHolder.bind(
            movies.value[position],
            movieListClickEvent
        )
    }

    override fun getItemCount(): Int {
        return 10_000
    }

    companion object {
        private const val MOVIE = 1
        private const val ADVERTISEMENT = 2
        private const val ADVERTISEMENT_TURN = 3
        private const val CYCLE = 4
    }
}
