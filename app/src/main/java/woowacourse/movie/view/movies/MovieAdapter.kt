package woowacourse.movie.view.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.movie.Movie

class MovieAdapter(
    private val movies: MutableList<Movie>,
    private val movieClickListener: MovieClickListener,
    private val advertisementClickListener: () -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int =
        if ((position + 1) % 4 == 0) MovieItemType.TYPE_ADVERTISEMENT.ordinal else MovieItemType.TYPE_MOVIE.ordinal

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder =
        if (viewType == MovieItemType.TYPE_MOVIE.ordinal) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
            MovieViewHolder(view, parent.context, movieClickListener)
        } else {
            val view =
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_advertisement, parent, false)
            AdvertisementViewHolder(view, advertisementClickListener)
        }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        if (holder is MovieViewHolder) {
            val item = movies[position]
            holder.bind(item)
        }
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = movies.size

    fun updateMovies(newMovies: List<Movie>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }
}
