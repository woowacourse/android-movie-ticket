package woowacourse.movie.movieList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.uiModel.AdUIModel
import woowacourse.movie.uiModel.MovieInfoUIModel
import woowacourse.movie.uiModel.MovieListItem
import woowacourse.movie.uiModel.PosterMapper

class MovieListAdapter(
    private val items: List<MovieListItem>,
    private val onClick: (MovieInfoUIModel) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val TYPE_MOVIE = 0
        private const val TYPE_AD = 1
    }

    override fun getItemViewType(position: Int): Int =
        when (items[position]) {
            is MovieInfoUIModel -> TYPE_MOVIE
            is AdUIModel -> TYPE_AD
            else -> throw IllegalArgumentException("Unknown item type")
        }

    override fun getItemCount(): Int = items.size

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

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (val item = items[position]) {
            is MovieInfoUIModel -> {
                val vh = holder as MovieViewHolder
                vh.bind(item)
                vh.button.setOnClickListener { onClick(item) }
            }

            is AdUIModel -> {
                (holder as AdViewHolder).bind()
            }
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

        fun bind(model: MovieInfoUIModel) {
            image.setImageResource(PosterMapper.getPosterResourceId(model.posterKey))
            title.text = model.title
            movieDate.text = itemView.context.getString(R.string.movie_date, model.startDate, model.endDate)
            runningTime.text = itemView.context.getString(R.string.running_time, model.runningTime)
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
