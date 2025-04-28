package woowacourse.movie.view.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.view.movie.MovieClickListener

class MovieAdapter(
    private val clickListener: MovieClickListener,
) : ListAdapter<Movie, RecyclerView.ViewHolder>(MoviesDiffUtil) {
    override fun getItemCount(): Int {
        val movieCount = super.getItemCount()
        val adCount = movieCount / MOVIE_COUNT
        return movieCount + adCount
    }

    override fun getItemViewType(position: Int): Int =
        if ((position + 1) % AD_INTERVAL == 0) {
            VIEW_TYPE_AD
        } else {
            VIEW_TYPE_MOVIE
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_MOVIE -> {
                val view = inflater.inflate(R.layout.item_movie, parent, false)
                MovieViewHolder(view, clickListener)
            }

            VIEW_TYPE_AD -> {
                val view = inflater.inflate(R.layout.item_advertisement, parent, false)
                AdViewHolder(view)
            }

            else -> throw IllegalArgumentException(ERROR_INVALID_VIEWTYPE)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is MovieViewHolder -> holder.bind(getItem(position))
            is AdViewHolder -> holder.bind()
        }
    }

    companion object {
        private const val VIEW_TYPE_MOVIE = 0
        private const val VIEW_TYPE_AD = 1
        private const val MOVIE_COUNT = 3
        private const val AD_INTERVAL = 4
        private const val ERROR_INVALID_VIEWTYPE = "지원하지 않는 viewType입니다."
    }
}
