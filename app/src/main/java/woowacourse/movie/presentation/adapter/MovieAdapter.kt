package woowacourse.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import woowacourse.movie.R
import woowacourse.movie.presentation.adapter.viewtype.MovieItemId
import woowacourse.movie.presentation.adapter.viewtype.MovieItemViewType
import woowacourse.movie.utils.formatScreeningPeriod

class MovieAdapter(
    private val onMovieItemClick: (Long) -> Unit,
    private val movieItems: List<MovieItemViewType>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            MovieItemId.MOVIE.id -> {
                val movieView = inflater.inflate(R.layout.movie_item, parent, false)
                MovieViewHolder(movieView, onMovieItemClick)
            }
            else -> {
                val adView = inflater.inflate(R.layout.ads_item, parent, false)
                AdViewHolder(adView)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is MovieViewHolder -> {
                holder.bind(movieItems[position] as MovieItemViewType.MovieView)
            }
            is AdViewHolder -> {
                holder.bind(movieItems[position] as MovieItemViewType.AdView)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (movieItems.isEmpty()) 0 else Int.MAX_VALUE
    }

    override fun getItemViewType(position: Int): Int {
        return movieItems[position].movieItemId.id
    }
}

class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val adsImg: ImageView = itemView.findViewById(R.id.ads)

    fun bind(data: MovieItemViewType.AdView) {
        adsImg.load(data.adsUrl)
    }
}

class MovieViewHolder(itemView: View, private val onMovieItemClick: (Long) -> Unit) : RecyclerView.ViewHolder(itemView) {
    private var movieId: Long = NOT_INIT_MOVIE_ID
    private val thumbnail: ImageView = itemView.findViewById(R.id.movieThumbnail)
    private val title: TextView = itemView.findViewById(R.id.movieTitle)
    private val date: TextView = itemView.findViewById(R.id.movieDate)
    private val runningTime: TextView = itemView.findViewById(R.id.movieRunningTime)
    private val reservation: Button = itemView.findViewById(R.id.movieReservation)

    init {
        reservation.setOnClickListener {
            onMovieItemClick(movieId)
        }
    }

    fun bind(data: MovieItemViewType.MovieView) {
        movieId = data.movie.id
        thumbnail.load(data.movie.thumbnailUrl)
        title.text = data.movie.title
        date.text = formatScreeningPeriod(data.movie.screenDateTime.map { it.dateTime })
        runningTime.text = "${data.movie.runningTime}"
    }

    companion object {
        const val NOT_INIT_MOVIE_ID = -1L
    }
}
