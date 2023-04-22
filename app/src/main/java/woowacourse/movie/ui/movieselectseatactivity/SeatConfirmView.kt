package woowacourse.movie.ui.movieselectseatactivity

import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import woowacourse.movie.R
import woowacourse.movie.ui.model.MovieUIModel

class SeatConfirmView(private val view: ViewGroup, movieData: MovieUIModel) {
    val tvMovieTitle: TextView = view.findViewById(R.id.tv_movie_title)
    val tvMovieTotalPrice: TextView = view.findViewById(R.id.tv_movie_total_price)
    val btnCheck: Button = view.findViewById(R.id.btn_check)

    init {
        initMovieData(movieData)
    }

    private fun initMovieData(movieData: MovieUIModel) {
        tvMovieTitle.text = movieData.title
    }

    fun updateMovieTotalPrice(price: Int) {
        tvMovieTotalPrice.text = price.toString()
    }

    fun updateBtnCheckState(readyState: Boolean) {
        btnCheck.isSelected = readyState
        btnCheck.isClickable = readyState
    }

    companion object {
        private const val MOVIE_TOTAL_PRICE_FORMAT = "%d원"
    }
}
