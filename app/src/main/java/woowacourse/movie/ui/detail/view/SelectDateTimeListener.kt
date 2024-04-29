package woowacourse.movie.ui.detail.view

fun interface SelectDateListener {
    fun selectDate(datePosition: Int)
}

fun interface SelectTimeListener {
    fun selectTime(timePosition: Int)
}
