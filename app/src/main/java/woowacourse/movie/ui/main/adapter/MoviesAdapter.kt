package woowacourse.movie.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.ui.model.AdvertisementUiModel
import woowacourse.movie.ui.model.MovieItem
import woowacourse.movie.ui.model.MovieItemType
import woowacourse.movie.ui.model.MovieUiModel

class MoviesAdapter(
    private val moviesItem: List<MovieItem>,
    private val onClick: (MovieItem) -> Unit,
) : RecyclerView.Adapter<BaseViewHolder<MovieItem>>() {
    override fun getItemViewType(position: Int): Int {
        return when (moviesItem[position]) {
            is MovieUiModel -> MovieItemType.MOVIE_TYPE.value
            is AdvertisementUiModel -> MovieItemType.ADVERTISEMENT_TYPE.value
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<MovieItem> {
        return when (viewType) {
            MovieItemType.MOVIE_TYPE.value -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
                MovieViewHolder(view, onClick)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.advertisement_item, parent, false)
                AdvertisementViewHolder(view)
            }
        } as? BaseViewHolder<MovieItem> ?: throw IllegalStateException("viewType=$viewType 대해 BaseViewHolder<MovieItem>의 서브타입을 생성할 수 없습니다.")
    }

    override fun getItemCount(): Int {
        return moviesItem.size
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<MovieItem>,
        position: Int,
    ) {
        holder.bind(moviesItem[position])
    }
}
