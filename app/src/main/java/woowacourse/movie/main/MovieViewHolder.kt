package woowacourse.movie.main

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.Movie
import woowacourse.movie.R

class MovieViewHolder(root: View) {
    var image: ImageView
    var title: TextView
    var startDate: TextView
    var endDate: TextView
    var time: TextView
    var reservation: Button

    init {
        image = root.findViewById(R.id.image)
        title = root.findViewById(R.id.title)
        startDate = root.findViewById(R.id.start_date)
        endDate = root.findViewById(R.id.end_date)
        time = root.findViewById(R.id.time)
        reservation = root.findViewById(R.id.reservation)
    }

    fun initSet(movie: Movie) {
        image.setImageResource(movie.imgResourceId)
        title.text = movie.title
        startDate.text = movie.startDate.format(MovieAdapter.DATE_TIME_FORMATTER)
        endDate.text = movie.endDate.format(MovieAdapter.DATE_TIME_FORMATTER)
        time.text = movie.runningTime.value.toString()
    }
}
