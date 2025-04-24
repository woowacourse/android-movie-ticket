package woowacourse.movie.contract

interface ReservationContract {
    interface Presenter {
        fun presentTitle()

        fun presentPeriod()
    }

    interface View {
        fun setTitle(title: String)

        fun setPeriod(
            startYear: Int,
            startMonth: Int,
            startDay: Int,
            endYear: Int,
            endMonth: Int,
            endDay: Int,
        )
    }
}
