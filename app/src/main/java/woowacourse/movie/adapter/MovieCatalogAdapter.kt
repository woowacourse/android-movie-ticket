package woowacourse.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieCatalogAdapter(
    private val context: Context,
    private val movies: List<Movie>,
    private val sendMovieId: (Int) -> Unit,
) : BaseAdapter() {
    inner class MovieViewHolder(view: View, movieId: Int) {
        private val reservationButton: Button = view.findViewById(R.id.item_movie_catalog_button_reservation)
        val title: TextView = view.findViewById(R.id.item_movie_catalog_text_view_title)
        val poster: ImageView = view.findViewById(R.id.item_movie_catalog_image_view_poster)
        val screeningDate: TextView = view.findViewById(R.id.item_movie_catalog_text_view_screening_date)
        val runningTime: TextView = view.findViewById(R.id.item_movie_catalog_text_view_running_time)

        init {
            reservationButton.setOnClickListener {
                sendMovieId(movieId)
            }
        }
    }

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View
        val movieViewHolder: MovieViewHolder
        val item: Movie = movies[position]

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movie_catalog, null)
            movieViewHolder = MovieViewHolder(view, item.id)
            view.tag = movieViewHolder
        } else {
            view = convertView
            movieViewHolder = view.tag as MovieViewHolder
        }

        movieViewHolder.title.text = item.title
        movieViewHolder.poster.setImageResource(item.poster)
        movieViewHolder.screeningDate.text = convertDateFormat(item.firstScreeningDate, item.lastScreeningDate)
        movieViewHolder.runningTime.text = item.runningTime

        return view
    }

    private fun convertDateFormat(
        firstDate: LocalDate,
        secondDate: LocalDate,
    ): String {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy.M.dd")

        return "${firstDate.format(dateFormat)} ~ ${secondDate.format(dateFormat)}"
    }
}
