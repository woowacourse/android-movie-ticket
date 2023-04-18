package woowacourse.movie.view

import android.os.Bundle
import woowacourse.movie.domain.Movie

class MovieDateTimePicker(
    val dateSpinner: DateSpinner,
    val timeSpinner: TimeSpinner,
) {

    fun makeView(movie: Movie, savedInstanceState: Bundle?) {
        dateSpinner.make(savedInstanceState, movie, timeSpinner)
    }

    fun save(outState: Bundle) {
        dateSpinner.save(outState)
        timeSpinner.save(outState)
    }
}
