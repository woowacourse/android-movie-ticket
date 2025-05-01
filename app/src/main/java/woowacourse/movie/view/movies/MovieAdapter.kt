package woowacourse.movie.view.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.movie.Movie

class MovieAdapter(
    items: List<Movie>,
    private val movieClickListener: MovieClickListener,
    private val advertisementClickListener: () -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var movies: List<Movie> = items

    override fun getItemViewType(position: Int): Int =
        when {
            (position + 1) % AD_POSITION_MULTIPLE == 0 -> AD_ITEM_TYPE
            else -> MOVIE_ITEM_TYPE
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder =
        when (viewType) {
            MOVIE_ITEM_TYPE -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
                val holder = MovieViewHolder(view, parent.context)
                holder.button.setOnClickListener {
                    val position = holder.adapterPosition
                    val adjustedPosition = position - position / AD_POSITION_MULTIPLE
                    val item = movies[adjustedPosition]
                    movieClickListener.onReservationClick(item.id)
                }
                holder
            }

            AD_ITEM_TYPE -> {
                val view =
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_advertisement, parent, false)
                AdvertisementViewHolder(view, advertisementClickListener)
            }

            else -> throw IllegalStateException("지원하지 않는 타입입니다.")
        }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is MovieViewHolder -> {
                val adjustedPosition = position - position / AD_POSITION_MULTIPLE
                val item = movies[adjustedPosition]
                holder.bind(item)
            }
        }
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = movies.size + (movies.size / AD_POSITION_INTERVAL)

    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    companion object {
        private const val AD_POSITION_INTERVAL = 3
        private const val AD_POSITION_MULTIPLE = 4
        private val MOVIE_ITEM_TYPE = R.layout.item_movie
        private val AD_ITEM_TYPE = R.layout.item_advertisement
    }
}
