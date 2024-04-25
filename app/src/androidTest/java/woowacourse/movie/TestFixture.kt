package woowacourse.movie

import androidx.test.espresso.DataInteraction
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matchers
import woowacourse.movie.db.ScreeningDao
import woowacourse.movie.model.Movie
import woowacourse.movie.model.ScreeningDateTime
import woowacourse.movie.model.Ticket

object TestFixture {
    const val FIRST_ITEM_POSITION = 0
    val movies: List<Movie> = ScreeningDao().findAll()

    val moviesFirstItem: DataInteraction =
        Espresso.onData(
            Matchers.anything(),
        ).inAdapterView(
            ViewMatchers.withId(R.id.list_view_reservation_home),
        ).atPosition(FIRST_ITEM_POSITION)

    fun makeMockTicket(): Ticket {
        val movie = movies[FIRST_ITEM_POSITION]
        val dateTime = ScreeningDateTime(movie.screeningPeriod[0].toString(), movie.screeningTimes.weekDay[0].toString())
        return Ticket(1, dateTime)
    }
}
