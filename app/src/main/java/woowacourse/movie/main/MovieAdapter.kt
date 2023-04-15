package woowacourse.movie.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.Movie
import woowacourse.movie.R
import java.time.format.DateTimeFormatter

class MovieAdapter(
    movie: List<Movie>,
    var clickListener: ReservationClickListener? = null
) : BaseAdapter() {

    private val _movie: List<Movie> = movie.toList()
    val movie: List<Movie>
        get() = _movie.toList()

    override fun getCount(): Int {
        return _movie.size
    }

    override fun getItem(position: Int): Movie {
        return _movie[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val context = parent?.context ?: return null
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.movie_item_layout, parent, false)

        val image = view.findViewById<ImageView>(R.id.image)
        val title = view.findViewById<TextView>(R.id.title)
        val startDate = view.findViewById<TextView>(R.id.start_date)
        val endDate = view.findViewById<TextView>(R.id.end_date)
        val time = view.findViewById<TextView>(R.id.time)
        val reservation = view.findViewById<Button>(R.id.reservation)

        image.setImageResource(_movie[position].imgResourceId)
        title.text = _movie[position].title
        startDate.text = _movie[position].startDate.format(DATE_TIME_FORMATTER)
        endDate.text = _movie[position].endDate.format(DATE_TIME_FORMATTER)
        time.text = _movie[position].runningTime.value.toString()

        reservation.setOnClickListener { clickListener?.onClick(position) }
        return view
    }

    interface ReservationClickListener {
        fun onClick(position: Int)
    }

//    class ViewHolder(
//
//    )

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.M.d")
    }
}
