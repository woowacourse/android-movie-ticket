package woowacourse.movie.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.Movie
import woowacourse.movie.R
import java.time.format.DateTimeFormatter

class MovieAdapter(
    movie: List<Movie>
) : BaseAdapter() {

    private val _movie: List<Movie> = movie.toList()
    val movie: List<Movie>
        get() = _movie.toList()

    var clickListener: ReservationClickListener? = null

    override fun getCount(): Int {
        return _movie.size
    }

    override fun getItem(position: Int): Movie {
        return _movie[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertview: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: MovieViewHolder

        if (convertview != null) {
            viewHolder = convertview.tag as MovieViewHolder
            view = convertview
        } else {
            view = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.movie_item_layout, parent, false)
            viewHolder = MovieViewHolder(view)
            view.tag = viewHolder
        }

        viewHolder.image.setImageResource(_movie[position].imgResourceId)
        viewHolder.title.text = _movie[position].title
        viewHolder.startDate.text = _movie[position].startDate.format(DATE_TIME_FORMATTER)
        viewHolder.endDate.text = _movie[position].endDate.format(DATE_TIME_FORMATTER)
        viewHolder.time.text = _movie[position].runningTime.value.toString()
        viewHolder.reservation.setOnClickListener { clickListener?.onClick(position) }
        return view
    }

    interface ReservationClickListener {
        fun onClick(position: Int)
    }

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.M.d")
    }
}
