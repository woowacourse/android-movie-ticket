package woowacourse.movie.movieList

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat.startActivity
import movie.Cinema
import movie.MovieSchedule
import woowacourse.movie.R
import woowacourse.movie.movieReservation.MovieReservationActivity
import woowacourse.movie.utils.DateUtil

class MovieListAdapter(
    private val Cinema: Cinema,
) : BaseAdapter() {
    override fun getCount(): Int {
        return Cinema.size
    }

    override fun getItem(position: Int): MovieSchedule {
        return Cinema[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: inflateMovieListItemView(parent)
        val viewHolder = view.tag as MovieListViewHolder

        with(Cinema[position]) {
            viewHolder.bind(
                posterResource = poster,
                title = title,
                date = DateUtil(view.context).getDateRange(startDate, endDate),
                runningTime = view.context.getString(R.string.movie_running_time).format(runningTime),
                reservationListener = {
                    val intent = Intent(view.context, MovieReservationActivity::class.java)
                    intent.putExtra(MovieReservationActivity.KEY_MOVIE_SCHEDULE, this)
                    startActivity(view.context, intent, null)
                },
            )
        }

        return view
    }

    private fun inflateMovieListItemView(parent: ViewGroup?): View {
        return View.inflate(
            parent?.context,
            R.layout.include_movie_list_item,
            null,
        ).apply {
            tag = MovieListViewHolder(
                posterView = this.findViewById(R.id.movie_poster),
                titleView = this.findViewById(R.id.movie_title),
                releaseDateView = this.findViewById(R.id.movie_release_date),
                runningTimeView = this.findViewById(R.id.movie_running_time),
                reservationButton = this.findViewById(R.id.movie_reservation_button),
            )
        }
    }
}
