package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.Movie

class MovieAdapter(private val movies: List<Movie>, private val onTicketingButtonClick: (Int) -> Unit) : BaseAdapter() {
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
        val ticketingButton = view.findViewById<Button>(R.id.btn_ticketing)

        val movie = movies[position]
        title.text = movie.title
        date.text = parent?.context?.getString(R.string.title_date, movie.date)
        runningTime.text = parent?.context?.getString(R.string.title_running_time, movie.runningTime)
        ticketingButton.setOnClickListener { onTicketingButtonClick(movie.id) }
        thumbnail.setImageResource(movie.thumbnail)

        return view
    }
}
