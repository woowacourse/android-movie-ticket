package woowacourse.movie

import android.content.Intent
import woowacourse.movie.model.Movie
import java.time.LocalDate

class MainPresenter : MainContract.Presenter {
    private var movieList: ArrayList<Movie> = arrayListOf()

    override fun putData(
        intent: Intent,
        position: Int,
    ) {
        val movie = item(position)
        intent.putExtra("title", movie.title)
        intent.putExtra("screenDate", movie.screenDateToString())
        intent.putExtra("runningTime", movie.runningTime.toString())
        intent.putExtra("description", movie.description)
        intent.putExtra("image", movie.img)
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
