package woowacourse.movie.screeningmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class MovieAdapter(
    private val movies: List<ScreeningItem>,
    private val onClickReservationButton: (id: Long) -> Unit = {},
) : RecyclerView.Adapter<ScreeningViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return when (movies[position]) {
            is ScreenMovieUiModel -> MOVIE
            is AdvertiseUiModel -> ADVERTISE
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ScreeningViewHolder {
        return when (viewType) {
            MOVIE -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_screening_movie, parent, false)
                MovieViewHolder(view, onClickReservationButton)
            }

            ADVERTISE -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_screening_advertise, parent, false)
                AdvertiseViewHolder(view)
            }

            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(
        holder: ScreeningViewHolder,
        position: Int,
    ) {
        when (holder) {
            is MovieViewHolder -> holder.onBind(movies[position] as ScreenMovieUiModel)
            is AdvertiseViewHolder -> { }
        }
    }

    override fun getItemCount(): Int = movies.size

    companion object {
        private const val MOVIE = 0
        private const val ADVERTISE = 1
    }
}
