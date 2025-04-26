package woowacourse.movie.domain.model.seat

sealed interface SeatPolicy {
    val price: Int

    object SGradePolicy : SeatPolicy {
        override val price: Int = 15_000
    }

    object BGradePolicy : SeatPolicy {
        override val price: Int = 10_000
    }

    object AGradePolicy : SeatPolicy {
        override val price: Int = 12_000
    }

    companion object {
        fun get(row: Int) =
            when (row) {
                1, 2 -> BGradePolicy
                3, 4 -> SGradePolicy
                else -> AGradePolicy
            }
    }
}
