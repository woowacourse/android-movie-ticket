package woowacourse.movie.view.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.viewmodel.MovieListData
import woowacourse.movie.view.viewmodel.MovieListData.ADData
import woowacourse.movie.view.viewmodel.MovieUIModel

class MovieRecyclerAdapter(
    private val listData: List<MovieListData>,
    private val onBookListener: OnClickItem,
    private val onClick: (ADData) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnClickItem {
        fun onClick(movie: MovieUIModel)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(viewGroup.context)
        return if (viewType == 1) {
            MovieViewHolder(layoutInflater.inflate(R.layout.movie_item, viewGroup, false))
        } else {
            ADHolder(layoutInflater.inflate(R.layout.advertisement, viewGroup, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (listData[position] is MovieUIModel) return 1
        else if (listData[position] is ADData) return 2

        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = listData[position]
        if (holder is MovieViewHolder) {
            holder.bind(item as MovieUIModel, onBookListener)
        } else if (holder is ADHolder) {
            holder.bind(item as ADData, onClick)
        }
    }

    override fun getItemCount() = listData.size
}
