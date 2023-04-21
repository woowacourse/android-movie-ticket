package woowacourse.movie.ui.main

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieState
import woowacourse.movie.ui.DateTimeFormatters

class MovieViewHolder(
    itemView: View,
    onClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {
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

        reservation.setOnClickListener { onClick(adapterPosition) }
    }

    fun bind(item: MovieState) {
        image.setImageResource(item.imgId)
        title.text = item.title
        date.text =
            DateTimeFormatters.convertToDateTildeDate(
                date.context,
                item.startDate,
                item.endDate
            )
        time.text = time.context.getString(R.string.running_time, item.runningTime)
    }
}
