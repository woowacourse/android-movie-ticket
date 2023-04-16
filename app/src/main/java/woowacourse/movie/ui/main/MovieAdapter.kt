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
        val item = _movie[position]
        val context = parent?.context ?: return null
        val view = convertView ?: run {
            val newView = LayoutInflater.from(context)
                .inflate(R.layout.movie_item_layout, parent, false)
            val image = newView.findViewById<ImageView>(R.id.image)
            val title = newView.findViewById<TextView>(R.id.title)
            val runningDate = newView.findViewById<TextView>(R.id.running_date)
            val runningTime = newView.findViewById<TextView>(R.id.running_time)
            val reservation = newView.findViewById<Button>(R.id.reservation)

            val viewHolder = MovieViewHolder(image, title, runningDate, runningTime, reservation)
            newView.tag = viewHolder
            newView
        }

        val holder = view.tag as MovieViewHolder
        holder.setViewHolderFromItem(item, position, clickListener)
        return view
    }

    interface ReservationClickListener {
        fun onClick(position: Int)
    }

    private class MovieViewHolder(
        private val image: ImageView,
        private val title: TextView,
        private val date: TextView,
        private val time: TextView,
        private val reservation: Button
    ) {
        fun setViewHolderFromItem(
            item: MovieState,
            position: Int,
            reservationClickListener: ReservationClickListener? = null
        ) {
            image.setImageResource(item.imgId)
            title.text = item.title
            date.text = date.context.getString(
                R.string.running_date,
                item.startDate.format(DATE_TIME_FORMATTER),
                item.endDate.format(DATE_TIME_FORMATTER)
            )
            time.text = time.context.getString(R.string.running_time, item.runningTime)
            reservation.setOnClickListener { reservationClickListener?.onClick(position) }
        }
    }

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.M.d")
    }
}
