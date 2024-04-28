package woowacourse.movie

import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matchers

object TestFixture {
    val moviesFirstItem =
        Espresso.onData(
            Matchers.anything(),
        ).inAdapterView(
            ViewMatchers.withId(R.id.recycler_view_reservation_home),
        ).atPosition(0)
}
