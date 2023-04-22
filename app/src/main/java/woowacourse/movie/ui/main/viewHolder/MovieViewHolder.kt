package woowacourse.movie.ui.main.viewHolder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.ui.DateTimeFormatters
import woowacourse.movie.ui.main.itemModel.ItemModel
import woowacourse.movie.ui.main.itemModel.MovieItemModel

class MovieViewHolder(
    itemView: View
) : ItemViewHolder(itemView) {
    private val image: ImageView
    private val title: TextView
    private val date: TextView
    private val time: TextView
    private val reservation: Button

    init {
        image = itemView.findViewById(R.id.image)
        title = itemView.findViewById(R.id.reservation_title)
        date = itemView.findViewById(R.id.running_date)
        time = itemView.findViewById(R.id.running_time)
        reservation = itemView.findViewById(R.id.reservation)
    }

    override fun bind(itemModel: ItemModel) {
        val item = itemModel as MovieItemModel
        image.setImageResource(item.movieState.imgId)
        title.text = item.movieState.title
        date.text =
            DateTimeFormatters.convertToDateTildeDate(
                date.context,
                item.movieState.startDate,
                item.movieState.endDate
            )
        time.text = time.context.getString(R.string.running_time, item.movieState.runningTime)

        reservation.setOnClickListener { item.onClick(bindingAdapterPosition) }
    }
}
