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
        return if (isAdCondition(position)) VIEW_TYPE_AD else VIEW_TYPE_MOVIE
    }

    override fun getItemCount() = (movies.size) + (movies.size / AD_PER_ROW)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isAdCondition(position)) {
            (holder as AdListViewHolder).bind(ad)
        } else {
            (holder as MovieListViewHolder).bind(movies[position - (position / AD_PER_ROW)])
        }
    }

    private fun isAdCondition(position: Int) =
        (position + 1) % (AD_PER_ROW + 1) == 0 && position > 0

    companion object {
        const val VIEW_TYPE_AD = 0
        const val VIEW_TYPE_MOVIE = 1
        const val AD_PER_ROW = 3
    }
}
