package woowacourse.movie.presentation.screen.movie.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import woowacourse.movie.R
import woowacourse.movie.model.Movie

class MovieAdapter(private val movie: (Movie) -> Unit) :
    ListAdapter<ScreenView, ScreenViewHolder>(movieViewComparator) {
    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is ScreenView.MovieView -> ScreenView.MovieView.id
            is ScreenView.AdView -> ScreenView.AdView.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ScreenViewHolder {
        return when (viewType) {
            ScreenView.MovieView.id -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
                ScreenViewHolder.MovieItem(view, movie)
            }

            ScreenView.AdView.id -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.movie_advertisment_item, parent, false)
                ScreenViewHolder.AdsItem(view)
            }

            else -> error("뷰타입에 해당하는 viewType=${viewType}가 없습니다")
        }
    }

    override fun onBindViewHolder(
        holder: ScreenViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        when (holder) {
            is ScreenViewHolder.MovieItem -> holder.bind((item as ScreenView.MovieView).item)
            is ScreenViewHolder.AdsItem -> holder.bind((item as ScreenView.AdView).res)
        }
    }

    companion object {
        private val movieViewComparator =
            object : DiffUtil.ItemCallback<ScreenView>() {
                override fun areItemsTheSame(
                    oldItem: ScreenView,
                    newItem: ScreenView,
                ): Boolean {
                    return oldItem == newItem
                }


                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: ScreenView,
                    newItem: ScreenView,
                ): Boolean {
                    return oldItem === newItem
                }
            }
    }
}
