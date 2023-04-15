package woowacourse.movie.movieList.movieListItem

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import movie.Screening
import woowacourse.movie.R
import woowacourse.movie.movieList.MovieListViewHolder
import woowacourse.movie.movieReservation.MovieReservationActivity
import woowacourse.movie.utils.DateUtil

object ScreeningItem : MovieListItem {
    override fun getView(screening: Screening, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: initMovieListItemView(parent)
        view.tag = view.tag ?: initViewHolder(view)
        bindViewHolder(view, screening)
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

    private fun bindViewHolder(view: View, screening: Screening) {
        val viewHolder = (view.tag) as MovieListViewHolder

        viewHolder.bind(
            posterResource = screening.poster,
            title = screening.title,
            date = DateUtil(view.context).getDateRange(screening.startDate, screening.endDate),
            runningTime = view.context.getString(R.string.movie_running_time).format(screening.runningTime),
            reservationListener = {
                val intent = Intent(view.context, MovieReservationActivity::class.java)
                intent.putExtra(MovieReservationActivity.KEY_MOVIE_SCHEDULE, screening)
                ContextCompat.startActivity(view.context, intent, null)
            },
        )
    }
}
