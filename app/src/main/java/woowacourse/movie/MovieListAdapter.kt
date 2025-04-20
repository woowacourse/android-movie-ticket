package woowacourse.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import woowacourse.movie.MovieTicketActivity.Companion.KEY_MOVIE
import woowacourse.movie.domain.Movie
import java.time.format.DateTimeFormatter

class MovieListAdapter(
    private val value: List<Movie>,
    private val onReservationClick: (selectedMovie: Movie) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int {
        return value.size
    }

    override fun getItem(position: Int): Movie {
        return value[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val movie = getItem(position)
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        } else {
            view = convertView
        }

        val viewHolder = ViewHolder(view)

        viewHolder.title.text = movie.title
        viewHolder.poster.setImageResource(movie.poster)
        viewHolder.screeningDate.text = viewHolder.screeningDate.context.getString(
            R.string.movie_screening_date,
            movie.screeningStartDate,
            movie.screeningEndDate
        )
        viewHolder.runningTime.text = viewHolder.runningTime.context.getString(
            R.string.movie_running_time,
            movie.runningTime
        )
        viewHolder.movieItem.setOnClickListener {
            onReservationClick.invoke(value[position])
        }

        return view
    }
}

private class ViewHolder(view: View) {
    val movieItem: View = view.findViewById(R.id.movie_item_root)
    val title: TextView = view.findViewById(R.id.movie_title)
    val poster: ImageView = view.findViewById(R.id.movie_poster)
    val screeningDate: TextView = view.findViewById(R.id.movie_date)
    val runningTime: TextView = view.findViewById(R.id.movie_running_time)
}
