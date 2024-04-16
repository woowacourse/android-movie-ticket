package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.Movie

class MovieAdapter(private val movies: List<Movie>) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, null)
        val title = view.findViewById<TextView>(R.id.tv_title)
        val date = view.findViewById<TextView>(R.id.tv_date)
        val thumbnail = view.findViewById<ImageView>(R.id.iv_thumbnail)
        val runningTime = view.findViewById<TextView>(R.id.tv_running_time)

        val movie = movies[position]
        title.text = movie.title
        date.text = "${date.text} ${movie.date}"
        runningTime.text = "${runningTime.text} ${movie.runningTime}"

        return view
    }
}
