package woowacourse.movie.screeningmovie

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

sealed class ScreeningViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class MovieViewHolder(
    itemView: View,
    private val onClickReservationButton: (id: Long) -> Unit,
) : ScreeningViewHolder(itemView) {
    private val postImageView: ImageView = itemView.findViewById(R.id.iv_movie_post)
    private val title: TextView = itemView.findViewById(R.id.tv_movie_title)
    private val date: TextView = itemView.findViewById(R.id.tv_movie_running_date)
    private val runningTime: TextView = itemView.findViewById(R.id.tv_movie_running_time)
    private val button: Button = itemView.findViewById(R.id.btn_movie_reservation)

    fun onBind(item: ScreenMovieUiModel) {
        postImageView.setImageResource(item.imageRes)
        title.text = item.title
        date.text = item.screenDate
        runningTime.text = item.runningTime
        button.setOnClickListener {
            onClickReservationButton(item.id)
        }
    }
}

class AdvertiseViewHolder(itemView: View) : ScreeningViewHolder(itemView)
