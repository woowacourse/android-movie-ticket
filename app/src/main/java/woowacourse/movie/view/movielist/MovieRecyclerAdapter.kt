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
    private val onClick: OnClickItem
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnClickItem {
        fun onClickMovie(position: Int)
        fun onClickAD(position: Int)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(viewGroup.context)
        return if (viewType == MOVIE_TYPE) {
            val movieHolder =
                MovieViewHolder(layoutInflater.inflate(R.layout.movie_item, viewGroup, false))
            movieHolder.apply {
                bookBtn.setOnClickListener {
                    onClick.onClickMovie(adapterPosition)
                }
            }
        } else {
            val adHoler = ADHolder(layoutInflater.inflate(R.layout.advertisement, viewGroup, false))
            adHoler.apply {
                ad.setOnClickListener { onClick.onClickAD(adapterPosition) }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (listData[position]) {
            is MovieUIModel -> MOVIE_TYPE
            is ADData -> AD_TYPE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = listData[position]

        if (holder is MovieViewHolder) {
            holder.bind(item as MovieUIModel)
        } else if (holder is ADHolder) {
            holder.bind(item as ADData)
        }
    }

    override fun getItemCount() = listData.size

    companion object {
        private const val MOVIE_TYPE = 1
        private const val AD_TYPE = 2
    }
}
