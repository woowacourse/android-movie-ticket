package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.ListItemType
import woowacourse.movie.domain.Movie
import woowacourse.movie.ui.MovieUiModel

class MovieListAdapter(
    private var items: List<ListItemType>,
    private val onItemClick: (Movie) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    fun setItems(newMovies: List<Movie>) {
        items = addAdsToMovies(newMovies)
        notifyDataSetChanged()
    }

    private fun addAdsToMovies(movies: List<Movie>): List<ListItemType> {
        val result = mutableListOf<ListItemType>()
        for (i in movies.indices) {
            result.add(ListItemType.MovieItem(movies[i]))
            if ((i + 1) % 3 == 0) {
                result.add(ListItemType.AdItem)
            }
        }
        return result
    }

    override fun getItemViewType(position: Int): Int =
        when (items[position]) {
            is ListItemType.MovieItem -> MOVIE_TYPE
            is ListItemType.AdItem -> AD_TYPE
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder =
        when (viewType) {
            MOVIE_TYPE -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
                MovieViewHolder(view)
            }
            else -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.ad_item, parent, false)
                AdViewHolder(view)
            }
        }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (val item = items[position]) {
            is ListItemType.MovieItem -> (holder as MovieViewHolder).bind(item.movie)
            is ListItemType.AdItem -> (holder as AdViewHolder).bind()
        }
    }

    override fun getItemCount(): Int = items.size

    inner class MovieViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = view.findViewById(R.id.movie_image)
        private val title: TextView = view.findViewById(R.id.title)
        private val startDate: TextView = view.findViewById(R.id.start_date)
        private val endDate: TextView = view.findViewById(R.id.end_date)
        private val runningTime: TextView = view.findViewById(R.id.running_time)
        private val button: Button = view.findViewById(R.id.reservation_button)

        fun bind(movie: Movie) {
            val movieUiModel = MovieUiModel.fromDomain(movie)
            image.setImageResource(movieUiModel.poster)
            title.text = movieUiModel.title
            startDate.text = movieUiModel.startDate
            endDate.text = movieUiModel.endDate
            runningTime.text = movieUiModel.runningTime

            button.setOnClickListener {
                onItemClick(movie)
            }
        }
    }

    inner class AdViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        private val adImage: ImageView = view.findViewById(R.id.ad_image)

        fun bind() {
            adImage.setImageResource(R.drawable.ad)
        }
    }

    companion object {
        private const val MOVIE_TYPE = 0
        private const val AD_TYPE = 1
    }
}
