package woowacourse.movie.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.Advertisement
import woowacourse.movie.model.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

sealed interface MediaContentViewHolder

class MovieHolder(view: View, val sendMovieId: (Int) -> Unit) :
    RecyclerView.ViewHolder(view), MediaContentViewHolder {
    private val reservationButton: Button =
        view.findViewById(R.id.item_movie_catalog_button_reservation)
    private val title: TextView = view.findViewById(R.id.item_movie_catalog_text_view_title)
    private val poster: ImageView = view.findViewById(R.id.item_movie_catalog_image_view_poster)
    private val screeningDate: TextView =
        view.findViewById(R.id.item_movie_catalog_text_view_screening_date)
    private val runningTime: TextView =
        view.findViewById(R.id.item_movie_catalog_text_view_running_time)

    fun bind(movie: Movie) {
        title.text = movie.title
        poster.setImageResource(movie.poster)
        screeningDate.text =
            convertDateFormat(movie.firstScreeningDate, movie.lastScreeningDate)
        runningTime.text = movie.runningTime
        reservationButton.setOnClickListener {
            sendMovieId(movie.id)
        }
    }

    private fun convertDateFormat(
        firstScreeningDate: LocalDate,
        lastScreeningDate: LocalDate,
    ): String {
        val dateFormat = DateTimeFormatter.ofPattern(DATE_PATTERN)

        return "${firstScreeningDate.format(dateFormat)} ~ ${lastScreeningDate.format(dateFormat)}"
    }

    companion object {
        private const val DATE_PATTERN = "yyyy.M.dd"
    }
}

class AdvertisementHolder(view: View) : RecyclerView.ViewHolder(view), MediaContentViewHolder {
    private val poster: ImageView = view.findViewById(R.id.item_advertisement)

    fun bind(advertisement: Advertisement) {
        poster.setImageResource(advertisement.poster)
    }
}
