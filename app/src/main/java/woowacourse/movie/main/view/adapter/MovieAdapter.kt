package woowacourse.movie.main.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.Movie

class MovieAdapter(
    private val movies: List<Movie>,
    private val onReservationButtonClick: (Long) -> Unit,
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int,
    ) {
        val movie = movies[position]
        holder.apply {
            thumbnail.setImageResource(movie.thumbnail)
            title.text = movie.title
            startDate.text = movie.date.startLocalDate.toString()
            endDate.text = movie.date.endLocalDate.toString()
            runningTime.text = movie.runningTime.toString()
        }
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumbnail: ImageView = itemView.findViewById(R.id.movieThumbnail)
        val title: TextView = itemView.findViewById(R.id.movieTitle)
        val startDate: TextView = itemView.findViewById(R.id.movieStartDate)
        val endDate: TextView = itemView.findViewById(R.id.movieEndDate)
        val runningTime: TextView = itemView.findViewById(R.id.movieRunningTime)
        private val reservationButton: Button = itemView.findViewById(R.id.movieReservationBtn)

        init {
            reservationButton.setOnClickListener {
                onReservationButtonClick(adapterPosition.toLong())
            }
        }
    }
}
