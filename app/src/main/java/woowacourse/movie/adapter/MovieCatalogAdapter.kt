package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.MovieUtils.convertPeriodFormat
import woowacourse.movie.R
import woowacourse.movie.model.Movie

class MovieCatalogAdapter(
    private val movies: List<Movie>,
    val movie: (Movie) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val view: View
        val movieViewHolder: MovieViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_catalog, parent, false)
            movieViewHolder = MovieViewHolder(view)
            view.tag = movieViewHolder
        } else {
            view = convertView
            movieViewHolder = convertView.tag as MovieViewHolder
        }

        val item: Movie = movies[position]
        with(movieViewHolder) {
            title.text = item.title
            poster.setImageResource(item.posterId)
            screeningDate.text = convertPeriodFormat(item.screeningPeriod)
            runningTime.text = item.runningTime
            reservationButton.setOnClickListener { movie(item) }
        }
        return view
    }

    class MovieViewHolder(view: View) {
        val title: TextView = view.findViewById(R.id.item_movie_catalog_text_view_title)
        val poster: ImageView = view.findViewById(R.id.item_movie_catalog_image_view_poster)
        val screeningDate: TextView = view.findViewById(R.id.item_movie_catalog_text_view_screening_date)
        val runningTime: TextView = view.findViewById(R.id.item_movie_catalog_text_view_running_time)
        val reservationButton: Button = view.findViewById(R.id.item_movie_catalog_button_reservation)
    }
}
