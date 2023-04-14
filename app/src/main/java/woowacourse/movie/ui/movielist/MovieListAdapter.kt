package woowacourse.movie.ui.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieListAdapter(
    private val movies: List<Movie>,
    private val itemButtonClickListener: ItemButtonClickListener,
) : BaseAdapter() {
    interface ItemButtonClickListener {
        fun onClick(position: Int)
    }

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val movie = movies[position]
        val viewHolder: MovieListViewHolder

        val itemView = if (convertView != null) {
            convertView.also { viewHolder = it.tag as MovieListViewHolder }
        } else {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.movie_item, parent, false)
            viewHolder = MovieListViewHolder(view)
            view.tag = viewHolder
            view
        }

        viewHolder.bind(movie)
        viewHolder.button.setOnClickListener { itemButtonClickListener.onClick(position) }

        return itemView
    }

    private fun Movie.getScreenDate(): String = "상영일: ${startDate.format()} ~ ${endDate.format()}"

    private fun LocalDate.format(): String = format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))

    private fun Movie.getRunningTime(): String = "러닝타임: ${runningTime}분"

    inner class MovieListViewHolder(
        view: View,
    ) {
        private val poster: ImageView = view.findViewById(R.id.item_poster)
        private val title: TextView = view.findViewById(R.id.item_title)
        private val date: TextView = view.findViewById(R.id.item_date)
        private val runningTime: TextView = view.findViewById(R.id.item_running_time)
        val button: Button = view.findViewById(R.id.item_booking_button)

        fun bind(movie: Movie) {
            poster.setImageResource(movie.poster)
            title.text = movie.title
            date.text = movie.getScreenDate()
            runningTime.text = movie.getRunningTime()
        }
    }
}
