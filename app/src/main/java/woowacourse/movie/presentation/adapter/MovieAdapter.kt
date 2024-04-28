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
import woowacourse.movie.domain.Movie
import woowacourse.movie.presentation.adapter.viewtype.MovieViewType
import woowacourse.movie.utils.formatScreeningPeriod

class MovieAdapter(
    private val onMovieItemClick: (Long) -> Unit,
    private val movies: List<Movie>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == VIEW_TYPE_AD) {
            val adView = inflater.inflate(R.layout.ads_item, parent, false)
            AdViewHolder(adView)
        } else {
            val movieView = inflater.inflate(R.layout.movie_item, parent, false)
            MovieViewHolder(movieView)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is MovieViewHolder -> {
                val movieHolder = holder as MovieViewHolder
                movieHolder.bind(MovieViewType.Screen(movies[0], onMovieItemClick))
            }
            is AdViewHolder -> {
                val adHolder = holder as AdViewHolder
                adHolder.bind()
            }
        }
    }

    override fun getItemCount(): Int {
        return if (movies.isEmpty()) 0 else Int.MAX_VALUE
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % AD_INTERVAL == AD_POSITION) {
            VIEW_TYPE_AD
        } else {
            VIEW_TYPE_MOVIE
        }
    }

    companion object {
        private const val VIEW_TYPE_AD = 1
        private const val VIEW_TYPE_MOVIE = 2
        private const val AD_POSITION = 3
        private const val AD_INTERVAL = 4
    }
}

class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val adsImg: ImageView = itemView.findViewById(R.id.ads)

    fun bind() {
        adsImg.load(SAMPLE_IMG_SRC)
    }

    companion object {
        private const val SAMPLE_IMG_SRC =
            "https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2F" +
                "prod-files-secure.s3.us-west-2.amazonaws.com%" +
                "2Ff71cbdcd-b763-41af-9bbb-42abdb18bd6a%2F924f98cd-0a25-4463-90b5-" +
                "c222313c4437%2F%25E1%2584%258B%25E1%2585%25AE%25E1%2584%2590%25E1%2585%25A6%25E1%2584" +
                "%258F%25E1%2585%25A9_%25E1%2584%2589%25E1%2585%25A1%25E1%2584%258B%25E1%2585%25B5%25E1%" +
                "2584%2590%25E1%2585%25B3_%25E1%2584%2592%25E1%2585%25A" +
                "6%25E1%2584%2583%25E1%2585%25A5_2000x1333.png&blockId=c2dd0879-c2b5-484e-bbe8-5545f70aa320&width=3600"
    }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val thumbnail: ImageView = itemView.findViewById(R.id.movieThumbnail)
    private val title: TextView = itemView.findViewById(R.id.movieTitle)
    private val date: TextView = itemView.findViewById(R.id.movieDate)
    private val runningTime: TextView = itemView.findViewById(R.id.movieRunningTime)
    private val reservation: Button = itemView.findViewById(R.id.movieReservation)

    fun bind(data: MovieViewType.Screen) {
        thumbnail.load(data.movie.thumbnailUrl)
        title.text = data.movie.title
        date.text = formatScreeningPeriod(data.movie.screenDateTime.map { it.dateTime })
        runningTime.text = "${data.movie.runningTime}"
        reservation.setOnClickListener {
            data.onMovieItemClick(data.movie.id)
        }
    }
}
