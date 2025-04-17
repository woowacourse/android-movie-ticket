package woowacourse.movie.domain

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.ReservationActivity
import java.time.format.DateTimeFormatter

class MyAdapter(private val items: List<Movie>) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view =
            convertView ?: LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.item, parent, false)

        val imageView: ImageView = view.findViewById(R.id.movie_image)
        val titleTextView: TextView = view.findViewById(R.id.movie_title)
        val dateTextView: TextView = view.findViewById(R.id.movie_date)
        val timeTextView: TextView = view.findViewById(R.id.movie_time)
        val reserveButton: Button = view.findViewById(R.id.reserve_button)

        val formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN)
        val startDateFormatted = items[position].date.startDate.format(formatter)
        val endDateFormatted = items[position].date.endDate.format(formatter)

        imageView.setImageResource(items[position].image)
        titleTextView.text = items[position].title
        dateTextView.text = parent?.context?.getString(R.string.movieDate, startDateFormatted, endDateFormatted)
        timeTextView.text = parent?.context?.getString(R.string.movieTime, items[position].time)

        reserveButton.setOnClickListener {
            val intent = Intent(parent?.context, ReservationActivity::class.java)
            val movie = Movie(items[position].image, items[position].title, items[position].date, items[position].time)
            intent.putExtra("movie", movie)
            parent?.context?.startActivity(intent)
        }
        return view
    }

    companion object {
        private const val DATETIME_PATTERN = "yyyy.M.d"
    }
}
