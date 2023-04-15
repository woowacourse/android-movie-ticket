package woowacourse.movie.movieList

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import movie.MovieSchedule
import woowacourse.movie.R
import woowacourse.movie.movieReservation.MovieReservationActivity
import woowacourse.movie.utils.DateUtil

object MovieListItemView {
    fun getView(movieSchedule: MovieSchedule, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: initMovieListItemView(parent)
        view.tag = view.tag ?: initViewHolder(view)
        bindViewHolder(view, movieSchedule)
        return view
    }

    private fun initMovieListItemView(parent: ViewGroup?): View = View.inflate(
        parent?.context,
        R.layout.include_movie_list_item,
        null,
    )

    private fun initViewHolder(view: View): MovieListViewHolder = MovieListViewHolder(
        posterView = view.findViewById(R.id.movie_poster),
        titleView = view.findViewById(R.id.movie_title),
        releaseDateView = view.findViewById(R.id.movie_release_date),
        runningTimeView = view.findViewById(R.id.movie_running_time),
        reservationButton = view.findViewById(R.id.movie_reservation_button),
    )

    private fun bindViewHolder(view: View, movieSchedule: MovieSchedule) {
        val viewHolder = (view.tag) as MovieListViewHolder

        viewHolder.bind(
            posterResource = movieSchedule.poster,
            title = movieSchedule.title,
            date = DateUtil(view.context).getDateRange(movieSchedule.startDate, movieSchedule.endDate),
            runningTime = view.context.getString(R.string.movie_running_time).format(movieSchedule.runningTime),
            reservationListener = {
                val intent = Intent(view.context, MovieReservationActivity::class.java)
                intent.putExtra(MovieReservationActivity.KEY_MOVIE_SCHEDULE, movieSchedule)
                startActivity(view.context, intent, null)
            },
        )
    }
}
