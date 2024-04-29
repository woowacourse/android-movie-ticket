package woowacourse.movie.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.ui.ReservationButtonClickListener

class MovieContentAdapter(
    private val movieContents: List<MovieContent>,
    private val reservationButtonClickListener: ReservationButtonClickListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int) =
        if (isAdsTurn(position)) {
            TYPE_ADS
        } else {
            TYPE_MOVIE
        }

    private fun isAdsTurn(position: Int) = position % CONTENT_SET_SIZE == MOVIE_CONTENT_COUNT

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        return if (viewType == TYPE_MOVIE) {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_movie_content, parent, false)
            MovieViewHolder(view, reservationButtonClickListener)
        } else {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_advertisement, parent, false)
            AdvertisementViewHolder(view)
        }
    }

    override fun getItemCount(): Int = movieContents.size

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val movieContent = movieContents[position]
        if (getItemViewType(position) == TYPE_MOVIE) {
            (holder as MovieViewHolder).bind(movieContent)
        } else {
            (holder as AdvertisementViewHolder).bind()
        }
    }

    companion object {
        private const val TYPE_ADS = 0
        private const val TYPE_MOVIE = 1
        private const val CONTENT_SET_SIZE = 4
        private const val MOVIE_CONTENT_COUNT = 3
    }
}
