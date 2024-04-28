package woowacourse.movie.ui.home.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.movie.Screen
import woowacourse.movie.repository.DummyMovieList

class ScreenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.item_imageview)
    private val title: TextView = itemView.findViewById(R.id.item_title_textview)
    private val runningTime: TextView = itemView.findViewById(R.id.item_running_time_textview)
    private val screenDate: TextView = itemView.findViewById(R.id.item_screen_date_textview)
    private val reservationButton: Button = itemView.findViewById(R.id.item_reservation_button)

    fun bind(
        screen: Screen,
        onReservationButtonClick: (Long) -> Unit,
    ) {
        val movie = DummyMovieList.find(screen.movieId)

        image.setImageResource(movie.imgResId)
        title.text = movie.title
        runningTime.text = movie.runningTime.toString()
        screenDate.text = movie.screenPeriodToString()
        reservationButton.setOnClickListener {
            onReservationButtonClick(screen.id)
        }
    }
}
