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

typealias OnClickReservationButton = (Movie) -> Unit

class MovieCatalogAdapter(
    private val movies: List<Movie>,
    private val advertisements: List<Advertisement>,
    private val movie: OnClickReservationButton,
) : RecyclerView.Adapter<ViewHolder>() {
    private val movieViewType = CatalogViewType.MOVIE.viewType
    private val advertisementViewType = CatalogViewType.ADVERTISEMENT.viewType

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
        when (CatalogViewType.from(getItemViewType(position))) {
            CatalogViewType.MOVIE -> {
                val item = movies[position]
                (holder as MovieViewHolder).bind(item, movie)
            }
            CatalogViewType.ADVERTISEMENT -> {
                val item = advertisements[position - CatalogViewType.ADVERTISEMENT.position]
                (holder as AdvertisementViewHolder).bind(item)
            }
        }
    }

    override fun getItemCount() = movies.size

    override fun getItemViewType(position: Int): Int {
        return if (position % CatalogViewType.ADVERTISEMENT.interval == CatalogViewType.ADVERTISEMENT.position) {
            advertisementViewType
        } else {
            movieViewType
        }
    }
}
