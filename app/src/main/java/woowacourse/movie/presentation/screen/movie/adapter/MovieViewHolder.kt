package woowacourse.movie.presentation.screen.movie.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.Movie

sealed class ScreenViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    class AdsItem(val view: View) :
        ScreenViewHolder(view) {
        private val banner = view.findViewById<ImageView>(R.id.advertisement_imageview)

        fun bind(drawable: Int) {
            banner.setImageResource(drawable)
        }
    }

    class MovieItem(
        val view: View,
        private val movie: (Movie) -> Unit,
    ) : ScreenViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.title)
        private val screenDate: TextView = view.findViewById(R.id.screen_date)
        private val runningTime: TextView = view.findViewById(R.id.running_time)
        private val image: ImageView = view.findViewById(R.id.poster_image)
        private val reservationButton: Button = view.findViewById(R.id.reservation_button)

        fun bind(item: Movie) {
            title.text = item.title
            image.setImageResource(item.img)
            screenDate.text = item.screenDateToString()
            runningTime.text = item.runningTime.toString()
            reservationButton.setOnClickListener { movie(item) }
        }
    }
}
