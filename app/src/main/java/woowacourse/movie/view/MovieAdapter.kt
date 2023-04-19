package woowacourse.movie.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import domain.Movie
import domain.Movies
import java.time.format.DateTimeFormatter

class MovieAdapter(
    private val movies: domain.Movies,
    private val onClickEvent: (domain.Movie) -> Unit
) : BaseAdapter() {
    override fun getCount(): Int = movies.value.size

    override fun getItem(position: Int): Any = movies.value[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, null)
            viewHolder = ViewHolder(
                poster = view.findViewById(R.id.item_movie_poster),
                title = view.findViewById(R.id.item_movie_title),
                movieDate = view.findViewById(R.id.item_movie_date),
                runningTime = view.findViewById(R.id.item_movie_running_time),
                reservationButton = view.findViewById(R.id.item_movie_reservation_button)
            )
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.setView(movies.value[position])
        viewHolder.setButtonClickListener(movies.value[position], onClickEvent)
        return view
    }

    class ViewHolder(
        var poster: ImageView?,
        var title: TextView?,
        var movieDate: TextView?,
        var runningTime: TextView?,
        var reservationButton: Button?
    ) {
        fun setView(movie: domain.Movie) {
            poster?.setImageResource(movie.imagePath.toInt())
            val dateFormat =
                DateTimeFormatter.ofPattern(movieDate?.context?.getString(R.string.movie_date_format))
            movieDate?.text = movieDate?.context?.getString(R.string.movie_date)?.format(
                dateFormat.format(movie.date.startDate),
                dateFormat.format(movie.date.endDate)
            )
            runningTime?.text =
                runningTime?.context?.getString(R.string.movie_running_time)
                    ?.format(movie.runningTime)
            title?.text = movie.title
        }

        fun setButtonClickListener(movie: domain.Movie, onClickEvent: (domain.Movie) -> Unit) {
            reservationButton?.setOnClickListener {
                onClickEvent(movie)
            }
        }
    }
}
