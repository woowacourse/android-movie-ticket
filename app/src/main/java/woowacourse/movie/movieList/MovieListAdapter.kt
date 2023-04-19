package woowacourse.movie.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import model.AdModel
import model.ItemRecycler
import model.ScreeningModel
import woowacourse.movie.R
import woowacourse.movie.movieList.viewHolder.AdViewHolder
import woowacourse.movie.movieList.viewHolder.MovieViewHolder

class MovieListAdapter(
    private val items: List<ItemRecycler>,
    private val onClickButton: (ScreeningModel) -> Unit,
    private val onAdClick: (AdModel) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_MOVIE) {
            MovieViewHolder(
                layoutInflater.inflate(R.layout.item_movie_movie_list, parent, false),
            )
        } else {
            AdViewHolder(
                layoutInflater.inflate(R.layout.item_ad_movie_list, parent, false),
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(items[position] as ScreeningModel, onClickButton)
            is AdViewHolder -> holder.bind(items[position] as AdModel, onAdClick)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = when (items[position]) {
        is ScreeningModel -> TYPE_MOVIE
        is AdModel -> TYPE_AD
    }

    companion object {
        private const val TYPE_MOVIE = 1
        private const val TYPE_AD = 2
    }
}
