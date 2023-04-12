package woowacourse.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.domain.Date
import woowacourse.movie.domain.Movie

class MovieListAdapter(
    private val context: Context,
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
        val itemView = LayoutInflater.from(context).inflate(R.layout.movie_item, null)

        val movie = movies[position]
        itemView.findViewById<ImageView>(R.id.item_poster).setImageResource(movie.poster)
        itemView.findViewById<TextView>(R.id.item_title).text = movie.title
        itemView.findViewById<TextView>(R.id.item_date).text = movie.date.toScreenDate()
        itemView.findViewById<TextView>(R.id.item_running_time).text = movie.time.toRunningTime()
        itemView.findViewById<Button>(R.id.item_booking_button).setOnClickListener { itemClickListener.onItemClick(position) }

        return itemView
    }

    private fun Date.toScreenDate(): String = "상영일: $year.$month.$day"

    private fun Int.toRunningTime(): String = "러닝타임: ${this}분"
}
