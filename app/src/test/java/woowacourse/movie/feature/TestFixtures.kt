package woowacourse.movie.feature

import woowacourse.movie.feature.reservation.MovieReservationPresenter

fun MovieReservationPresenter.setUpReservationCount(count: Int) {
    initializeReservationCount()
    repeat(count - 1) { increaseReservationCount() }
}
