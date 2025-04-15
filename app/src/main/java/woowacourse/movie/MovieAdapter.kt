package woowacourse.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MovieAdapter(
    val onClickBooking: (Int) -> Unit,
    private val items: List<Movie>,
) : BaseAdapter() {
    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Movie = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view =
            convertView ?: LayoutInflater.from(parent?.context)
                .inflate(R.layout.movie_item, parent, false)
        return bind(view, position)
    }

    private fun bind(
        convertView: View,
        position: Int,
    ): View {
        val moviePoster = convertView.findViewById<ImageView>(R.id.img_poster)
        val movieTitle = convertView.findViewById<TextView>(R.id.tv_title)
        val movieReleaseDate = convertView.findViewById<TextView>(R.id.tv_release_date)
        val movieRunningTime = convertView.findViewById<TextView>(R.id.tv_running_time)
        val bookingBtn = convertView.findViewById<Button>(R.id.btn_booking)

        val item = getItem(position)

        moviePoster.setImageResource(item.poster)
        movieTitle.text = item.title
        movieReleaseDate.text = item.releaseDate
        movieRunningTime.text = item.runningTime

        bookingBtn.setOnClickListener {
            onClickBooking(position)
        }

        return convertView
    }
}
