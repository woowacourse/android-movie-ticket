package woowacourse.movie

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MovieListViewHolder {
    var poster: ImageView? = null
    var title: TextView? = null
    var screeningPeriod: TextView? = null
    var runningTime: TextView? = null
    var bookButton: Button? = null

    fun initView(view: View) {
        poster = view.findViewById(R.id.iv_movie_poster)
        title = view.findViewById(R.id.tv_movie_title)
        screeningPeriod = view.findViewById(R.id.tv_movie_screening_period)
        runningTime = view.findViewById(R.id.tv_movie_running_time)
        bookButton = view.findViewById(R.id.bt_book_now)
    }

    fun setData(context: Context, movieData: Movie) {
        poster?.setImageResource(movieData.poster)
        title?.text = movieData.title
        screeningPeriod?.text = context.getString(R.string.movie_screening_period)
            .format(
                DateFormatter.format(movieData.startDate),
                DateFormatter.format(movieData.endDate)
            )
        runningTime?.text =
            context.getString(R.string.movie_running_time).format(movieData.runningTime)
    }
}
