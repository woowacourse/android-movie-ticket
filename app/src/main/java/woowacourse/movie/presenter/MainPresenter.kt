package woowacourse.movie.presenter

import android.content.Intent
import woowacourse.movie.contract.MainContract
import woowacourse.movie.model.Movie
import java.time.LocalDate

class MainPresenter : MainContract.Presenter {
    private var movieList: ArrayList<Movie> = arrayListOf()

    override fun putData(
        intent: Intent,
        position: Int,
    ) {
        val movie = item(position)
        intent.putExtra("movie", movie)
    }

    fun registerMovie(
        title: String,
        screenDate: List<LocalDate>,
        runningTime: Int,
        description: String,
        imgSrc: Int,
    ) {
        movieList.add(
            Movie(
                title,
                screenDate,
                runningTime,
                description,
                imgSrc,
            ),
        )
    }

    fun movieList(): ArrayList<Movie> = movieList

    fun item(position: Int): Movie {
        require(position <= movieList.size) { "Position out of bound" }
        return movieList[position]
    }
}
