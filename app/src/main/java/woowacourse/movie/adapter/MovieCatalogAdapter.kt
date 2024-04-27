package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.model.Movie

class MovieCatalogAdapter(
    private val movies: List<Movie>,
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
        val item = movies[position]
        if (getItemViewType(position) == movieViewType) {
            (holder as MovieViewHolder).bind(item, movie)
        } else {
            (holder as AdvertisementViewHolder).bind()
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
