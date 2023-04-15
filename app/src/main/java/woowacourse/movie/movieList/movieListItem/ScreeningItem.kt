package woowacourse.movie.movieList.movieListItem

import android.view.View
import android.view.ViewGroup
import entity.MovieListDto
import entity.Screening
import woowacourse.movie.R
import woowacourse.movie.utils.DateUtil

object ScreeningItem : MovieListItem {
    override fun getView(movieListDto: MovieListDto, convertView: View?, parent: ViewGroup?): View {
        val screening = movieListDto as? Screening ?: throw IllegalArgumentException("Invalid type")
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

    private fun initViewHolder(view: View): ScreeningItemViewHolder = ScreeningItemViewHolder(
        posterView = view.findViewById(R.id.movie_poster),
        titleView = view.findViewById(R.id.movie_title),
        releaseDateView = view.findViewById(R.id.movie_release_date),
        runningTimeView = view.findViewById(R.id.movie_running_time),
    )

    private fun bindViewHolder(view: View, screening: Screening) {
        val viewHolder = (view.tag) as? ScreeningItemViewHolder ?: initViewHolder(view)

        val movie = screening.movie
        val reservation = screening.reservation
        viewHolder.bind(
            posterResource = movie.poster,
            title = movie.title,
            date = DateUtil(view.context).getDateRange(reservation.startDate, reservation.endDate),
            runningTime = view.context.getString(R.string.movie_running_time).format(movie.runningTime),
        )
    }
}
