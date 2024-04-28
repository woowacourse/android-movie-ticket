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

    inner class AdvertisementHolder(view: View) : ViewHolder(view) {
        val advertisementImageView: ImageView = view.findViewById(R.id.item_advertisement)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        return if (viewType == TYPE_MOVIE) {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_movie_catalog, parent, false)
            MovieHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_advertisement, parent, false)
            AdvertisementHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if ((position + 1) % 4 == 0) TYPE_ADVERTISEMENT else TYPE_MOVIE
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is MovieHolder -> {
                val item = movies[position]
                holder.title.text = item.title
                holder.poster.setImageResource(item.poster)
                holder.screeningDate.text =
                    convertDateFormat(item.firstScreeningDate, item.lastScreeningDate)
                holder.runningTime.text = item.runningTime
                holder.reservationButton.setOnClickListener {
                    sendMovieId(item.id)
                }
            }

            else -> {
                (holder as AdvertisementHolder)
            }
        }
    }

    override fun getItemCount(): Int = movies.size

    private fun convertDateFormat(
        firstDate: LocalDate,
        secondDate: LocalDate,
    ): String {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy.M.dd")

        return "${firstDate.format(dateFormat)} ~ ${secondDate.format(dateFormat)}"
    }

    companion object {
        private const val TYPE_MOVIE = 0
        private const val TYPE_ADVERTISEMENT = 1
    }
}
