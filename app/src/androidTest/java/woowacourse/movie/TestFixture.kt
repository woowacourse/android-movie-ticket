package woowacourse.movie

import androidx.test.espresso.DataInteraction
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matchers
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieStorage

object TestFixture {
    const val FIRST_ITEM_POSITION = 0
    val movies: List<Movie> = MovieStorage.obtainMovies()

    val moviesFirstItem: DataInteraction =
        Espresso.onData(
            Matchers.anything(),
        ).inAdapterView(
            ViewMatchers.withId(R.id.list_view_reservation_home),
        ).atPosition(FIRST_ITEM_POSITION)
}
