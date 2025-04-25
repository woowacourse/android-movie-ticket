package woowacourse.movie.feature.movies.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
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

    private class MovieViewHolder(
        private val view: View,
    ) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.tv_movie_title)
        private val poster: ImageView = view.findViewById(R.id.iv_movie_poster)
        private val date: TextView = view.findViewById(R.id.tv_movie_date)
        private val runningTime: TextView = view.findViewById(R.id.tv_movie_running_time)
        private val bookingButton: Button = view.findViewById(R.id.btn_movie_booking)

        fun bind(
            movie: Item.Movie,
            onBookingClick: (MovieUiModel) -> Unit,
        ) {
            title.text = movie.value.title
            poster.setImageResource(movie.value.poster)

            date.text =
                view.context.getString(
                    R.string.movies_movie_date_with_tilde,
                    movie.value.startDate,
                    movie.value.endDate,
                )

            runningTime.text =
                view.context.getString(
                    R.string.movies_movie_running_time,
                    movie.value.runningTime,
                )

            bookingButton.setOnClickListener { onBookingClick(movie.value) }
        }
    }

    override fun submitList(list: List<Item?>?) {
        if (itemCount + (list?.size ?: 0) > 10000) return else super.submitList(list)
    }

    private class AdvertisementViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view)

    object DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(
            oldItem: Item,
            newItem: Item,
        ): Boolean = oldItem.id == newItem.id && oldItem::class == newItem::class

        override fun areContentsTheSame(
            oldItem: Item,
            newItem: Item,
        ): Boolean = oldItem == newItem
    }
}
