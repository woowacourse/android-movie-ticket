package woowacourse.movie.adapter.viewholder

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.activity.MovieDetailActivity
import woowacourse.movie.model.MovieListItem

class MovieViewHolder(view: View) : CustomViewHolder(view) {
    private val image: ImageView = view.findViewById(R.id.img_movie)
    private val title: TextView = view.findViewById(R.id.text_title)
    private val playingDate: TextView = view.findViewById(R.id.text_playing_date)
    private val runningTime: TextView = view.findViewById(R.id.text_running_time)
    private val ticketingButton: Button = view.findViewById(R.id.btn_ticketing)

    override fun bind(data: MovieListItem) {
        data as MovieListItem.MovieModel
        val context = itemView.context
        image.setImageResource(data.image)
        title.text = data.title
        val playingDateText = context.getString(R.string.playing_time, data.startDate, data.endDate)
        playingDate.text = playingDateText
        val runningTimeText = context.getString(R.string.running_time, data.runningTime)
        runningTime.text = runningTimeText

        ticketingButton.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_KEY, data)
            context.startActivity(intent)
        }
    }

    companion object {
        private const val MOVIE_KEY = "movie"
    }
}
