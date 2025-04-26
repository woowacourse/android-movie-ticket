package woowacourse.movie.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.ui.mapper.PosterMapper
import woowacourse.movie.ui.view.utils.setImage

class MovieAdapter(
    private val movies: List<Movie>,
    private val onReservationClickListener: (Int) -> Unit,
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(itemView, onReservationClickListener)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int,
    ) {
        holder.bind(movies[position])
    }

    class MovieViewHolder(
        itemView: View,
        private val onReservationClickListener: (Int) -> Unit,
    ) : RecyclerView.ViewHolder(itemView) {
        private val reservation: Button = itemView.findViewById(R.id.reservation)
        private val imagePoster: ImageView = itemView.findViewById(R.id.poster)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val screeningDate: TextView = itemView.findViewById(R.id.screeningDate)
        private val runningTime: TextView = itemView.findViewById(R.id.runningTime)
//        private val advertisement: ImageView = itemView.findViewById(R.id.advertisement)

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

//            advertisement.setImage(advertisement.id)
        }
    }
}
