package woowacourse.movie.feature.movies.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.feature.model.MovieUiModel

class MoviesAdapter(
    private val onBookingClick: (MovieUiModel) -> Unit,
) : ListAdapter<Item, RecyclerView.ViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ItemViewType.MOVIE.type ->
                MovieViewHolder(
                    inflater.inflate(R.layout.item_movie, parent, false),
                )

            ItemViewType.ADVERTISEMENT.type ->
                AdvertisementViewHolder(
                    inflater.inflate(R.layout.item_advertisement, parent, false),
                )

            else -> throw IllegalArgumentException("[ERROR] 알 수 없는 뷰 타입 오류입니다.")
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val item: Item = getItem(position)

        when (holder) {
            is MovieViewHolder -> holder.bind(item as Item.Movie, onBookingClick)
            is AdvertisementViewHolder -> Unit
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is Item.Movie -> ItemViewType.MOVIE.type
            is Item.Advertisement -> ItemViewType.ADVERTISEMENT.type
        }

    override fun submitList(list: List<Item?>?) {
        if (itemCount + (list?.size ?: 0) > 10000) return else super.submitList(list)
    }
}
