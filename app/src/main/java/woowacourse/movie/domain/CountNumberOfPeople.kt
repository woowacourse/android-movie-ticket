package woowacourse.movie.domain

class CountNumberOfPeople {

    operator fun plus(numberOfBooker: Int): Int {
        var number = numberOfBooker
        number += 1
        if (number >= MAX_BOOKER_NUMBER) {
            number = MAX_BOOKER_NUMBER
        }
        return number
    }

    operator fun minus(numberOfBooker: Int): Int {
        var number = numberOfBooker
        number -= 1
        if (number <= MIN_BOOKER_NUMBER) {
            number = MIN_BOOKER_NUMBER
        }
        return number
    }

    companion object {
        private const val MIN_BOOKER_NUMBER = 1
        private const val MAX_BOOKER_NUMBER = 10
    }
}
