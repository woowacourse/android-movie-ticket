package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.model.Advertisement
import woowacourse.movie.model.MediaContent
import woowacourse.movie.model.Movie

class MovieAdapter(
    private val mediaContents: List<MediaContent>,
    private val sendMovieId: (Int) -> Unit,
) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int = mediaContents.size

    override fun getItemViewType(position: Int): Int = if (isTypeAdvertisement(position)) TYPE_ADVERTISEMENT else TYPE_MOVIE

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)

        return if (viewType == TYPE_MOVIE) {
            MovieHolder(view.inflate(R.layout.item_movie_catalog, parent, false), sendMovieId)
        } else {
            AdvertisementHolder(view.inflate(R.layout.item_advertisement, parent, false))
        }
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is MovieHolder -> {
                val movie = mediaContents[position] as Movie
                holder.bind(movie)
            }

            is AdvertisementHolder -> {
                val advertisement = mediaContents[position] as Advertisement
                holder.bind(advertisement)
            }
        }
    }

    private fun isTypeAdvertisement(position: Int): Boolean = mediaContents[position] is Advertisement

    companion object {
        private const val TYPE_MOVIE = 1
        private const val TYPE_ADVERTISEMENT = 2
    }
}
