package woowacourse.movie.adapter.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.listener.ItemClickListener
import woowacourse.movie.model.MovieListItem

class MovieViewHolder(view: View) : CustomViewHolder(view) {
    private val image: ImageView = view.findViewById(R.id.img_movie)
    private val title: TextView = view.findViewById(R.id.text_title)
    private val playingDate: TextView = view.findViewById(R.id.text_playing_date)
    private val runningTime: TextView = view.findViewById(R.id.text_running_time)
    private val ticketingButton: Button = view.findViewById(R.id.btn_ticketing)

    override fun bind(item: MovieListItem, clickListener: ItemClickListener) {
        item as MovieListItem.MovieModel
        val context = itemView.context
        image.setImageResource(item.image)
        title.text = item.title
        val playingDateText = context.getString(R.string.playing_time, item.startDate, item.endDate)
        playingDate.text = playingDateText
        val runningTimeText = context.getString(R.string.running_time, item.runningTime)
        runningTime.text = runningTimeText

        ticketingButton.setOnClickListener {
            clickListener.onClick(item)
        }
    }
}
