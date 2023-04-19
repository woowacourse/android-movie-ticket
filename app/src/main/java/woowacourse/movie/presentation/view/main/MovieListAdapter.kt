package woowacourse.movie.presentation.view.main

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.databinding.ItemAdvertiseBinding
import woowacourse.movie.databinding.ItemMovieListBinding
import woowacourse.movie.model.Movie

class MovieListAdapter(private val movies: List<Movie>, private val ad: Drawable) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_AD) {
            val binding =
                ItemAdvertiseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AdListViewHolder(binding)
        }
        val binding =
            ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 3 == 0 && position > 0) VIEW_TYPE_AD else VIEW_TYPE_MOVIE
    }

    override fun getItemCount() = (movies.size) + (movies.size / 3)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position % 3 == 0 && position > 0) {
            (holder as AdListViewHolder).bind(ad)
        } else {
            (holder as MovieListViewHolder).bind(movies[position - (position / 3)])
        }
    }

    companion object {
        const val VIEW_TYPE_AD = 0
        const val VIEW_TYPE_MOVIE = 1
    }
}
