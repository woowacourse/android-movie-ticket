package woowacourse.movie.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.MovieState
import java.time.format.DateTimeFormatter

class MovieAdapter(
    movie: List<MovieState>,
    var clickListener: ReservationClickListener? = null
) : BaseAdapter() {

    private val _movie: List<MovieState> = movie.toList()
    val movie: List<MovieState>
        get() = _movie.toList()

    override fun getCount(): Int {
        return _movie.size
    }

    override fun getItem(position: Int): MovieState {
        return _movie[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val context = parent?.context ?: return null
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.movie_item_layout, parent, false)
        val item = _movie[position]

        if (convertView == null) {
            val image = view.findViewById<ImageView>(R.id.image)
            val title = view.findViewById<TextView>(R.id.title)
            val runningDate = view.findViewById<TextView>(R.id.running_date)
            val runningTime = view.findViewById<TextView>(R.id.running_time)
            val reservation = view.findViewById<Button>(R.id.reservation)
            val viewHoler = MovieViewHolder(image, title, runningDate, runningTime, reservation)
            view.tag = viewHoler
        }

        val holder = view.tag as MovieViewHolder
        holder.image.setImageResource(_movie[position].imgId)
        holder.title.text = _movie[position].title
        holder.date.text = context.getString(
            R.string.running_date,
            item.startDate.format(DATE_TIME_FORMATTER),
            item.endDate.format(DATE_TIME_FORMATTER)
        )
        holder.time.text = context.getString(R.string.running_time, item.runningTime)

        holder.reservation.setOnClickListener { clickListener?.onClick(position) }
        return view
    }

    interface ReservationClickListener {
        fun onClick(position: Int)
    }

    private class MovieViewHolder(
        val image: ImageView,
        val title: TextView,
        val date: TextView,
        val time: TextView,
        val reservation: Button
    )

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.M.d")
    }
}
