package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieAdapter(
    private val movies: List<Movie>,
    private val sendMovieId: (Int) -> Unit,
) : RecyclerView.Adapter<ViewHolder>() {
    inner class MovieHolder(view: View) : ViewHolder(view) {
        val reservationButton: Button =
            view.findViewById(R.id.item_movie_catalog_button_reservation)
        val title: TextView = view.findViewById(R.id.item_movie_catalog_text_view_title)
        val poster: ImageView = view.findViewById(R.id.item_movie_catalog_image_view_poster)
        val screeningDate: TextView =
            view.findViewById(R.id.item_movie_catalog_text_view_screening_date)
        val runningTime: TextView =
            view.findViewById(R.id.item_movie_catalog_text_view_running_time)
    }

    inner class AdvertisementHolder(view: View) : ViewHolder(view)

    override fun getItemCount(): Int = movies.size

    override fun getItemViewType(position: Int): Int {
        return if (isTypeAdvertisement(position)) TYPE_ADVERTISEMENT else TYPE_MOVIE
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)

        return if (viewType == TYPE_MOVIE) {
            MovieHolder(view.inflate(R.layout.item_movie_catalog, parent, false))
        } else {
            AdvertisementHolder(view.inflate(R.layout.item_advertisement, parent, false))
        }
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is MovieHolder -> {
                val movie = movies[position]
                holder.title.text = movie.title
                holder.poster.setImageResource(movie.poster)
                holder.screeningDate.text =
                    convertDateFormat(movie.firstScreeningDate, movie.lastScreeningDate)
                holder.runningTime.text = movie.runningTime
                holder.reservationButton.setOnClickListener {
                    sendMovieId(movie.id)
                }
            }

            is AdvertisementHolder -> {}

            else -> throw IllegalArgumentException("존재 하지 않는 ViewHolder 입니다.")
        }
    }

    private fun isTypeAdvertisement(position: Int): Boolean = (position + 1) % 4 == 0

    private fun convertDateFormat(
        firstScreeningDate: LocalDate,
        lastScreeningDate: LocalDate,
    ): String {
        val dateFormat = DateTimeFormatter.ofPattern(DATE_PATTERN)

        return "${firstScreeningDate.format(dateFormat)} ~ ${lastScreeningDate.format(dateFormat)}"
    }

    companion object {
        private const val TYPE_MOVIE = 1
        private const val TYPE_ADVERTISEMENT = 2
        private const val DATE_PATTERN = "yyyy.M.dd"
    }
}
