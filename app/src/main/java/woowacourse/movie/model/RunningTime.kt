package woowacourse.movie.model

class RunningTime(private val time: Int) {
    init {
        require(time>0){ "상영시간은 존재해야한다." }
    }
}