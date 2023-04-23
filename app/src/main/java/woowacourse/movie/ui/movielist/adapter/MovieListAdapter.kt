package woowacourse.movie.ui.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieListModel

class MovieListAdapter(
    private val modelItems: List<MovieListModel>,
    private val onItemClick: ItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            MovieListViewType.MOVIE.value ->
                MovieItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
                ) {
                    onItemClick.onMovieItemClick(modelItems[it] as MovieListModel.MovieModel)
                }
            MovieListViewType.AD.value ->
                AdItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_ad, parent, false)
                ) {
                    onItemClick.onAdItemClick(modelItems[it] as MovieListModel.AdModel)
                }
            else -> throw IllegalStateException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (modelItems[position]) {
            is MovieListModel.MovieModel -> MovieListViewType.MOVIE.value
            is MovieListModel.AdModel -> MovieListViewType.AD.value
        }
    }

    override fun getItemCount(): Int = modelItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieItemViewHolder -> holder.bind(modelItems[position] as MovieListModel.MovieModel)
            is AdItemViewHolder -> holder.bind(modelItems[position] as MovieListModel.AdModel)
        }
    }
}
