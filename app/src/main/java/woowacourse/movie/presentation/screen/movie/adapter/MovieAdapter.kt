package woowacourse.movie.presentation.screen.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.Movie

class MovieAdapter(private val movie: (Movie) -> Unit) :
    ListAdapter<Movie, RecyclerView.ViewHolder>(movieComparator) {
    override fun getItemViewType(position: Int): Int {
        if ((position + 1) % ADS_INDEX == 0)
            return MovieViewHolderType.ADS.id
        return MovieViewHolderType.MOVIE.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MOVIE -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
                MovieViewHolder(view, movie)
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_advertisment_item, parent, false)
                AdvertisementViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // viewholder -> item
        val item = getItem(position)
        when (holder) {
            is MovieViewHolder -> (holder).bind(item)
            is AdvertisementViewHolder -> Unit
        }
    }

    companion object {
        private val movieComparator = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }

        const val ADS_INDEX: Int = 5
        const val VIEW_TYPE_MOVIE: Int = 0
    }
}
