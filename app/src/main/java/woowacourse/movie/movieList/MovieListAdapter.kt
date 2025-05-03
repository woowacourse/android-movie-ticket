package woowacourse.movie.movieList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.uiModel.MovieInfoUIModel
import woowacourse.movie.uiModel.PosterMapper

class MovieListAdapter(
    private val items: List<MovieInfoUIModel>,
    private val onClick: (MovieInfoUIModel) -> Unit,
    private val onError: () -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val TYPE_MOVIE = 0
        private const val TYPE_AD = 1
        private const val AD_INTERVAL = 4
    }

    override fun getItemViewType(position: Int): Int = if ((position + 1) % AD_INTERVAL == 0) TYPE_AD else TYPE_MOVIE

    override fun getItemCount(): Int {
        if (items.isEmpty()) return 0
        val groupSize = AD_INTERVAL - 1
        val adCount = items.size / groupSize
        return items.size + adCount
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_MOVIE -> {
                val view = inflater.inflate(R.layout.movie_list_item, parent, false)
                MovieViewHolder(view)
            }

            TYPE_AD -> {
                val view = inflater.inflate(R.layout.movie_list_ad, parent, false)
                AdViewHolder(view)
            }

            else -> throw IllegalArgumentException("Unknown viewType: $viewType")
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        if (holder is MovieViewHolder) {
            val realPos = position - (position / AD_INTERVAL)
            val movie = items.getOrNull(realPos)
            if (movie == null) {
                onError()
                return
            }
            holder.bind(movie)
            holder.button.setOnClickListener {
                onClick(movie)
            }
        } else if (holder is AdViewHolder) {
            holder.bind()
        }
    }

    class MovieViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = view.findViewById(R.id.movie_image)
        private val title: TextView = view.findViewById(R.id.title)
        private val movieDate: TextView = view.findViewById(R.id.movie_date)
        private val runningTime: TextView = view.findViewById(R.id.running_time)
        val button: Button = view.findViewById(R.id.reservation_button)

        fun bind(movieInfoUIModel: MovieInfoUIModel) {
            image.setImageResource(PosterMapper.getPosterResourceId(movieInfoUIModel.posterKey))
            title.text = movieInfoUIModel.title
            movieDate.text =
                itemView.context.getString(
                    R.string.movie_date,
                    movieInfoUIModel.startDate,
                    movieInfoUIModel.endDate,
                )
            runningTime.text =
                itemView.context.getString(
                    R.string.running_time,
                    movieInfoUIModel.runningTime,
                )
        }
    }

    class AdViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        private val adImage: ImageView = view.findViewById(R.id.ad)

        fun bind() {
            adImage.setImageResource(R.drawable.ad_image)
        }
    }
}
