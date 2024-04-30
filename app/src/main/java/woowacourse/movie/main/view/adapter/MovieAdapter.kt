package woowacourse.movie.main.view.adapter

import AdvertisementViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.model.Movie

class MovieAdapter(
    private val movies: List<Movie>,
    private val onReservationButtonClick: (Long) -> Unit,
) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        if (position % ADVERTISEMENT_OFFSET == 0 && position != 0) return ADVERTISEMENT_VIEW_TYPE
        return MOVIE_VIEW_TYPE
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        if (viewType == ADVERTISEMENT_VIEW_TYPE) {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_advertisement, parent, false)
            return AdvertisementViewHolder(view)
        }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        val movie = movies[position]
        when (holder) {
            is MovieViewHolder -> holder.bind(movie, onReservationButtonClick)
            is AdvertisementViewHolder -> holder.bind(ADVERTISEMENT_LINK)
        }
    }

    override fun getItemId(position: Int): Long = movies[position].id

    override fun getItemCount(): Int {
        return movies.size + movies.size / ADVERTISEMENT_OFFSET
    }

    companion object {
        private const val MOVIE_VIEW_TYPE = 0
        private const val ADVERTISEMENT_VIEW_TYPE = 1
        private const val ADVERTISEMENT_OFFSET = 3
        private const val ADVERTISEMENT_LINK = "https://www.woowacourse.io/"
    }
}
