package woowacourse.movie.movielist

import MovieViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.dto.AdDto
import woowacourse.movie.dto.MovieDto

class MovieRVAdapter(
    private val movies: List<MovieDto>,
    private val ad: AdDto,
    private val onMovieClicklistener: OnClickListener<MovieDto>,
    private val onAdClickListener: OnClickListener<AdDto>,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (ViewType.values()[viewType]) {
            ViewType.MOVIE_VIEW -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_item, parent, false)
                MovieViewHolder(view, onMovieClicklistener)
            }
            ViewType.AD_VIEW -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.ad_item, parent, false)
                AdViewHolder(view, onAdClickListener)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        when (holder.itemViewType) {
            ViewType.MOVIE_VIEW.ordinal -> (holder as MovieViewHolder).bind(item)
            ViewType.AD_VIEW.ordinal -> (holder as AdViewHolder).bind(ad)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if ((position + 1) % 4 == 0) return ViewType.AD_VIEW.ordinal else return ViewType.MOVIE_VIEW.ordinal
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
