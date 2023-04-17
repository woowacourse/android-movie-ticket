package woowacourse.movie

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MovieListViewHolder {
    var poster: ImageView? = null
    var title: TextView? = null
    var releaseDate: TextView? = null
    var runningTime: TextView? = null
    var bookButton: Button? = null

    fun initView(view: View) {
        poster = view.findViewById(R.id.iv_movie_poster)
        title = view.findViewById(R.id.tv_movie_title)
        releaseDate = view.findViewById(R.id.tv_movie_release_date)
        runningTime = view.findViewById(R.id.tv_movie_running_time)
        bookButton = view.findViewById(R.id.bt_book_now)
    }

    fun setData(movieData: Movie) {
        poster?.setImageResource(movieData.poster)
        title?.text = movieData.title
        releaseDate?.text = movieData.releaseDate
        runningTime?.text = movieData.runningTime
    }
}
