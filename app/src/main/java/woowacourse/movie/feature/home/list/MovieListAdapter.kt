package woowacourse.movie.feature.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.feature.home.list.viewholder.MovieAdvertisementViewHolder
import woowacourse.movie.feature.home.list.viewholder.MovieViewHolder
import woowacourse.movie.feature.home.listener.ReservationButtonClickListener
import woowacourse.movie.feature.home.ui.MovieAdvertisementUiModel
import woowacourse.movie.feature.home.ui.MovieListUiModel
import woowacourse.movie.feature.home.ui.MovieUiModel

class MovieListAdapter(
    private val movies: List<MovieListUiModel>,
    private val reservationButtonClickListener: ReservationButtonClickListener,
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        return if (viewType == MovieListViewType.MOVIE.id) {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
            MovieViewHolder(itemView, reservationButtonClickListener)
        } else {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_advertisement, parent, false)
            MovieAdvertisementViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        if (movies[position].viewType == MovieListViewType.MOVIE) {
            (holder as MovieViewHolder).bind(movies[position] as MovieUiModel)
            return
        }
        (holder as MovieAdvertisementViewHolder).bind(movies[position] as MovieAdvertisementUiModel)
    }

    override fun getItemCount(): Int = movies.size

    override fun getItemViewType(position: Int): Int = movies[position].viewType.id
}
