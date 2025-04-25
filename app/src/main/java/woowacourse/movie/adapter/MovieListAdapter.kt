package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.ui.MovieUiModel

class MovieListAdapter(
    private var movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit,
) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    fun setItems(newItems: List<Movie>) {
        movies = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int,
    ) {
        val item = movies[position]
        val movieUiModel = MovieUiModel.fromDomain(item)

        with(holder) {
            image.setImageResource(movieUiModel.poster)
            title.text = movieUiModel.title
            startDate.text = movieUiModel.startDate
            endDate.text = movieUiModel.endDate
            runningTime.text = movieUiModel.runningTime
        }
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.movie_image)
        val title: TextView = view.findViewById(R.id.title)
        val startDate: TextView = view.findViewById(R.id.start_date)
        val endDate: TextView = view.findViewById(R.id.end_date)
        val runningTime: TextView = view.findViewById(R.id.running_time)
        val button: Button = view.findViewById(R.id.reservation_button)

        init {
            button.setOnClickListener {
                onMovieClicked(bindingAdapterPosition)
            }
        }

        private val onMovieClicked = { position: Int ->
            onItemClick.invoke(movies[position])
        }
    }
}
