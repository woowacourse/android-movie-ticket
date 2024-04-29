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
                holder.bind(MovieViewType.Screen(movies[0], onMovieItemClick))
            }
            is AdViewHolder -> {
                holder.bind()
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
            "https://s3-alpha-sig.figma.com/img/e1aa/0753/a796f5d7ba7df41bb1cfaa64dd2d8167?" +
                "Expires=1715558400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=Ht2jSxu-~W6VC3LYqfcpwmWkfqJs9qXmBjqzUFp" +
                "kLalMeo3TFLrJlJJDwGl8EfhRtRweS5c8ZHpyg6vyaGJ-3wvGO~gjmtGyiw-LWvIfhz7QpwamHn3M~" +
                "fFOFD8SBgd~WhNsBzHV7lVT2jm3wongpDHxoJbg733-od8dYRQMJOy-YFS0OIRGSwAKsoNSEOqR7trP8tQCfQliSjo81VBqX" +
                "tutKmHXXDCPeDu-7uDCf~rnfiRmjgdQMPs8NhCniJXPuPjZnJXL0ebKO0ivMkPCXDN0HaawxE1doNn3xYtXb9" +
                "60mP9U0oTWI3am74uNyXgx5fC1wAQB0HeJy~wg9ZpjSA__"
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
