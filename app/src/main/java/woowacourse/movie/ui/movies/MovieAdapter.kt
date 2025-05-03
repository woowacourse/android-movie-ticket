package woowacourse.movie.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.domain.movies.Movie
import woowacourse.movie.ui.movies.poster.PosterMapper
import woowacourse.movie.ui.movies.poster.setImage

class MovieAdapter(
    private val movies: List<Movie>,
    private val onReservationClickListener: (Int) -> Unit,
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder =
        if (viewType == ADVERTISEMENT_TYPE) {
            val itemView =
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_advertisement, parent, false)
            AdvertisementViewHolder(itemView)
        } else {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
            MovieViewHolder(itemView, onReservationClickListener)
        }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is MovieViewHolder -> {
                val realPosition = position - (position / 4)
                holder.bind(movies[realPosition])
            }

            is AdvertisementViewHolder -> {
                holder.bindAdvertisement()
            }
        }
    }

    override fun getItemViewType(position: Int): Int = if ((position + 1) % 4 == 0) ADVERTISEMENT_TYPE else MOVIE_TYPE

    override fun getItemCount(): Int {
        val adCount = movies.size / 3
        return movies.size + adCount
    }

    class MovieViewHolder(
        itemView: View,
        private val onReservationClickListener: (Int) -> Unit,
    ) : ViewHolder(itemView) {
        private val reservation: Button = itemView.findViewById(R.id.reservation)
        private val imagePoster: ImageView = itemView.findViewById(R.id.poster)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val screeningDate: TextView = itemView.findViewById(R.id.screeningDate)
        private val runningTime: TextView = itemView.findViewById(R.id.runningTime)

        fun bind(movie: Movie) {
            reservation.setOnClickListener {
                onReservationClickListener.invoke(movie.id)
            }

            title.text = movie.title
            screeningDate.text =
                itemView.context.getString(
                    R.string.date_text,
                    movie.startScreeningDate,
                    movie.endScreeningDate,
                )
            runningTime.text =
                itemView.context.getString(
                    R.string.runningTime_text,
                    movie.runningTime.toString(),
                )

            val posterRes = PosterMapper.mapMovieIdToDrawableRes(movie.id)
            imagePoster.setImage(posterRes)
        }
    }

    class AdvertisementViewHolder(
        itemView: View,
    ) : ViewHolder(itemView) {
        private val advertisement = itemView.findViewById<ImageView>(R.id.advertisement)

        fun bindAdvertisement() {
            advertisement.setImageResource(R.drawable.advertisement)
        }
    }

    companion object {
        private const val ADVERTISEMENT_TYPE = 1
        private const val MOVIE_TYPE = 0
    }
}
