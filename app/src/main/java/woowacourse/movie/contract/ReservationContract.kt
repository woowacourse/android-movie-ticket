package woowacourse.movie.contract

interface ReservationContract {
    interface Presenter {
        fun presentPoster()

        fun presentTitle()

        fun presentPeriod()

        fun presentRunningTime()
    }

    interface View {
        fun setPoster(movieId: Int) {}

        fun setTitle(title: String)

        fun setPeriod(
            startYear: Int,
            startMonth: Int,
            startDay: Int,
            endYear: Int,
            endMonth: Int,
            endDay: Int,
        )

        fun setRunningTime(runningTime: Int) {}
    }
}
