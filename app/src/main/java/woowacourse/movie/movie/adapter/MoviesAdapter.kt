package woowacourse.movie.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.movie.MovieListItem

class MoviesAdapter(
    private val items: List<MovieListItem>,
    private val onClick: (Movie) -> Unit,
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val holder: ViewHolder

        when (viewType) {
            R.layout.movie_item -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
                holder = MoviesViewHolder(view)

                holder.reserveBtn.setOnClickListener {
                    val position = holder.getAdapterPosition()
                    onClick((items[position] as MovieListItem.MovieItem).movie)
                }
            }
            R.layout.advertisement_item -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.advertisement_item, parent, false)
                holder = AdViewHolder(view)
            }
            else -> throw IllegalArgumentException(INVALID_VIEW_TYPE)
        }

        return holder
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is MovieListItem.MovieItem -> R.layout.movie_item
            is MovieListItem.AdvertisementItem -> R.layout.advertisement_item
        }
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        val item = items[position]

        when (holder) {
            is MoviesViewHolder -> holder.binding((item as MovieListItem.MovieItem).movie)
            is AdViewHolder -> holder.binding(item as MovieListItem.AdvertisementItem)
        }
    }

    override fun getItemCount(): Int = items.count()

    companion object {
        private const val INVALID_VIEW_TYPE: String = "지원하지 않는 아이템 타입입니다"
    }
}
