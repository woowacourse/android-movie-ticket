package woowacourse.movie.adapter

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
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.global.setImage
import woowacourse.movie.global.toFormattedDate

class MovieListAdapter(
    private val movies: List<MovieDto>,
    private val onReservationClick: (selectedMovie: MovieDto) -> Unit,
) : ListAdapter<MovieDto, MovieListAdapter.ViewHolder>(MyDataDiffCallback()) {
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        holder.setData(movies[position], onReservationClick)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item, parent, false)
        return ViewHolder(binding)
    }

    private class MyDataDiffCallback : DiffUtil.ItemCallback<MovieDto>() {
        override fun areItemsTheSame(
            oldItem: MovieDto,
            newItem: MovieDto,
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: MovieDto,
            newItem: MovieDto,
        ): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById<TextView>(R.id.movie_title)
        val runningTimeTextView: TextView = view.findViewById<TextView>(R.id.movie_running)
        val screeningDateTextView: TextView = view.findViewById<TextView>(R.id.movie_date)
        val posterImageView: ImageView = view.findViewById<ImageView>(R.id.movie_poster)
        val buttonView: Button = view.findViewById<Button>(R.id.btn_book)

        fun setData(
            movie: MovieDto,
            onReservationClick: (selectedMovie: MovieDto) -> Unit,
        ) {
            titleTextView.text = movie.title
            runningTimeTextView.text =
                runningTimeTextView.context.getString(
                    R.string.movie_running_time,
                    movie.runningTime.inWholeMinutes,
                )
            screeningDateTextView.text =
                screeningDateTextView.context.getString(
                    R.string.movie_screening_date,
                    movie.startDateTime.toFormattedDate(),
                    movie.endDateTime.toFormattedDate(),
                )
            posterImageView.setImage(movie.drawable)
            buttonView.setOnClickListener {
                onReservationClick(movie)
            }
        }
    }
}
