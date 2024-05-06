package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.model.Advertisement
import woowacourse.movie.model.MediaContents
import woowacourse.movie.model.Movie

class MovieAdapter(
    private val mediaContents: MediaContents,
    private val sendMovieId: (Int) -> Unit,
) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int = mediaContents.value.size

    override fun getItemViewType(position: Int): Int = mediaContents.mediaContentsType(position)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)

        return when (viewType) {
            MediaContents.TYPE_MOVIE -> {
                MovieHolder(view.inflate(R.layout.item_movie_catalog, parent, false), sendMovieId)
            }

            MediaContents.TYPE_ADVERTISEMENT -> {
                AdvertisementHolder(view.inflate(R.layout.item_advertisement, parent, false))
            }

            else -> {
                throw IllegalArgumentException("존재하지 않는 MediaContent 타입입니다.")
            }
        }
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is MovieHolder -> {
                val movie = mediaContents.value[position] as Movie
                holder.bind(movie)
            }

            is AdvertisementHolder -> {
                val advertisement = mediaContents.value[position] as Advertisement
                holder.bind(advertisement)
            }
        }
    }
}
