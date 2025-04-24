package woowacourse.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.domain.Movie

class MoviesAdapter(
    private val movies: List<Movie>,
    private val onClick: (Movie) -> Unit,
) : RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        val holder = MoviesViewHolder(view)

        holder.reserveBtn.setOnClickListener {
            val position = holder.getAdapterPosition()
            onClick(movies[position])
        }

        return holder
    }

    override fun onBindViewHolder(
        holder: MoviesViewHolder,
        position: Int,
    ) {
        val item = movies[position]

        holder.binding(item)
    }

    override fun getItemCount(): Int = movies.count()
}
