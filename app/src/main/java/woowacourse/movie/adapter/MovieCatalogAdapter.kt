package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.Movie

class MovieCatalogAdapter(
    private val movies: List<Movie>,
    val movie: (Movie) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == 0) {
            val view = inflater.inflate(R.layout.item_movie_catalog, parent, false)
            MovieViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_advertisement, parent, false)
            AdvertisementViewHolder(view)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val item = movies[position]
        if (getItemViewType(position) == MOVIE) {
            (holder as MovieViewHolder).bind(item, movie)
        } else {
            (holder as AdvertisementViewHolder).bind()
        }
    }

    override fun getItemCount() = movies.size

    override fun getItemViewType(position: Int): Int {
        return if (position % ADVERTISEMENT_INTERVAL == ADVERTISEMENT_POSITION) {
            ADVERTISEMENT
        } else {
            MOVIE
        }
    }

    companion object {
        const val MOVIE = 0
        const val ADVERTISEMENT = 1
        const val ADVERTISEMENT_INTERVAL = 4
        const val ADVERTISEMENT_POSITION = 3
    }
}
