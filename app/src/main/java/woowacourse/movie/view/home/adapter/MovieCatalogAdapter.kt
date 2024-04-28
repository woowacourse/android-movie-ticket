package woowacourse.movie.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.model.advertisement.Advertisement
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.view.home.adapter.viewholder.AdvertisementViewHolder
import woowacourse.movie.view.home.adapter.viewholder.MovieViewHolder

class MovieCatalogAdapter(
    private val movies: List<Movie>,
    private val advertisements: List<Advertisement>,
    val movie: (Movie) -> Unit,
) : RecyclerView.Adapter<ViewHolder>() {
    private val movieViewType = CatalogViewType.MOVIE.viewType
    private val advertisementType = CatalogViewType.ADVERTISEMENT.viewType

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == movieViewType) {
            val view = inflater.inflate(R.layout.item_movie_catalog, parent, false)
            MovieViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_advertisement, parent, false)
            AdvertisementViewHolder(view)
        }
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        if (getItemViewType(position) == movieViewType) {
            val item = movies[position]
            (holder as MovieViewHolder).bind(item, movie)
        } else {
            val item = advertisements[position - ADVERTISEMENT_POSITION]
            (holder as AdvertisementViewHolder).bind(item)
        }
    }

    override fun getItemCount() = movies.size

    override fun getItemViewType(position: Int): Int {
        return if (position % ADVERTISEMENT_INTERVAL == ADVERTISEMENT_POSITION) {
            advertisementType
        } else {
            movieViewType
        }
    }

    companion object {
        const val ADVERTISEMENT_INTERVAL = 4
        const val ADVERTISEMENT_POSITION = 3
    }
}
