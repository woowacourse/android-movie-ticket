package woowacourse.movie.ui.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.ui.dto.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieListAdapter(
    private val movies: List<Movie>,
    private val onItemButtonClick: (Movie) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            itemView = LayoutInflater.from(parent?.context).inflate(R.layout.movie_item, parent, false)
            viewHolder = ViewHolder(itemView)
            itemView.tag = viewHolder
        } else {
            itemView = convertView
            viewHolder = itemView.tag as ViewHolder
        }

        viewHolder.bind(movies[position], onItemButtonClick)

        return itemView
    }

    class ViewHolder(view: View) {
        private val moviePoster: ImageView = view.findViewById(R.id.item_poster)
        private val movieTitle: TextView = view.findViewById(R.id.item_title)
        private val movieDate: TextView = view.findViewById(R.id.item_date)
        private val movieTime: TextView = view.findViewById(R.id.item_running_time)
        private val bookingButton: Button = view.findViewById(R.id.item_booking_button)

        fun bind(movie: Movie, onItemButtonClick: (Movie) -> Unit) {
            moviePoster.setImageResource(movie.poster)
            movieTitle.text = movie.title
            movieDate.text = movie.getScreenDate()
            movieTime.text = movie.getRunningTime()
            bookingButton.setOnClickListener { onItemButtonClick(movie) }
        }

        private fun Movie.getScreenDate(): String = movieDate.context.getString(R.string.screen_date, startDate.format(), endDate.format())

        private fun LocalDate.format(): String = format(DateTimeFormatter.ofPattern(movieDate.context.getString(R.string.date_format)))

        private fun Movie.getRunningTime(): String = movieTime.context.getString(R.string.running_time, runningTime)
    }
}
