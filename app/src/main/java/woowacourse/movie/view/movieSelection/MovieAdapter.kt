package woowacourse.movie.view.movieSelection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.view.model.MovieUiModel
import java.time.format.DateTimeFormatter

class MovieAdapter(
    private val movies: List<MovieUiModel>,
    private val onReservationClick: (MovieUiModel) -> Unit,
) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(
        object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie,
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie,
            ): Boolean {
                return oldItem == newItem
            }
        },
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view, onReservationClick)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int,
    ) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(
        val view: View,
        private val onReservationClick: (MovieUiModel) -> Unit,
    ) : RecyclerView.ViewHolder(view) {
        private val poster: ImageView = view.findViewById(R.id.poster)
        private val title: TextView = view.findViewById(R.id.movie_title)
        private val screeningDate: TextView = view.findViewById(R.id.screening_date)
        private val runningTime: TextView = view.findViewById(R.id.running_time)
        private val reserveButton: Button = view.findViewById(R.id.reserve_button)

        init {
            reserveButton.setOnClickListener { onReservationClick }
        }

        fun bind(movie: MovieUiModel) {
            val formatter =
                DateTimeFormatter.ofPattern(view.context.getString(R.string.date_format))
            val startDate = movie.startDate.format(formatter)
            val endDate = movie.endDate.format(formatter)

            poster.setImageResource(movie.poster)
            title.text = movie.title
            screeningDate.text =
                view.context.getString(R.string.screening_dates_format, startDate, endDate)
            runningTime.text =
                view.context.getString(R.string.running_type_format, movie.runningTime)
            reserveButton.setOnClickListener { onReservationClick(movie) }
        }
    }
}
