package woowacourse.movie.model.movie

class RunningTime(val time: Int) {
    init {
        require(time > 0) { "상영시간은 존재해야한다." }
    }
}
