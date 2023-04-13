package woowacourse.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.domain.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieListAdapter(
    private val movies: List<Movie>,
    private val itemClickListener: ItemClickListener,
) : BaseAdapter() {
    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView =
            LayoutInflater.from(parent?.context).inflate(R.layout.movie_item, parent, false)

        val movie = movies[position]
        itemView.findViewById<ImageView>(R.id.item_poster).setImageResource(movie.poster)
        itemView.findViewById<TextView>(R.id.item_title).text = movie.title
        itemView.findViewById<TextView>(R.id.item_date).text = movie.getScreenDate()
        itemView.findViewById<TextView>(R.id.item_running_time).text = movie.getRunningTime()
        itemView.findViewById<Button>(R.id.item_booking_button)
            .setOnClickListener { itemClickListener.onItemClick(position) }

        return itemView
    }

    private fun Movie.getScreenDate(): String = "상영일: ${startDate.format()} ~ ${endDate.format()}"

    private fun LocalDate.format(): String = format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))

    private fun Movie.getRunningTime(): String = "러닝타임: ${runningTime}분"
}
