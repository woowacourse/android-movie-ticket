package woowacourse.movie.view.home.adapter.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.utils.MovieUtils.convertPeriodFormat

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val title: TextView = view.findViewById(R.id.item_movie_catalog_text_view_title)
    private val poster: ImageView = view.findViewById(R.id.item_movie_catalog_image_view_poster)
    private val screeningDate: TextView = view.findViewById(R.id.item_movie_catalog_text_view_screening_date)
    private val runningTime: TextView = view.findViewById(R.id.item_movie_catalog_text_view_running_time)
    private val reservationButton: Button = view.findViewById(R.id.item_movie_catalog_button_reservation)

    fun bind(
        item: Movie,
        movie: (Movie) -> Unit,
    ) {
        title.text = item.title
        poster.setImageResource(item.posterId)
        screeningDate.text = convertPeriodFormat(item.screeningPeriod)
        runningTime.text = item.runningTime
        reservationButton.setOnClickListener { movie(item) }
    }
}
