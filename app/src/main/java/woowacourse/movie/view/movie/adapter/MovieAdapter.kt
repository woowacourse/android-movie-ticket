package woowacourse.movie.view.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.view.ReservationUiFormatter
import woowacourse.movie.view.movie.MovieClickListener

class MovieAdapter(
    private val clickListener: MovieClickListener,
) : ListAdapter<Movie, RecyclerView.ViewHolder>(
        object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie,
            ): Boolean = oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie,
            ): Boolean = oldItem == newItem
        },
    ) {
    private val reservationUiFormatter: ReservationUiFormatter by lazy { ReservationUiFormatter() }

    override fun getItemCount(): Int {
        val movieCount = super.getItemCount()
        val adCount = movieCount / 3
        return movieCount + adCount
    }

    override fun getItemViewType(position: Int): Int =
        // 3, 7, 11.. 포지션 광고
        if (position % 4 == 3) {
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
                MovieViewHolder(view, clickListener, reservationUiFormatter)
            }

            else -> {
                val view = inflater.inflate(R.layout.item_advertisement, parent, false)
                AdViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is MovieViewHolder -> holder.bind(getMovieItem(position))
            is AdViewHolder -> holder.bind()
        }
    }

    private fun getMovieItem(position: Int): Movie {
        val adCount = (position + 1) / 4
        val moviePosition = position - adCount
        return getItem(moviePosition)
    }

    companion object {
        private const val VIEW_TYPE_MOVIE = 0
        private const val VIEW_TYPE_AD = 1
    }
}
