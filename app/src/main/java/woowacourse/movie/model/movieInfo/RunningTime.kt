package woowacourse.movie.model.movieInfo

import java.io.Serializable

class RunningTime(private val time: Int) : Serializable {
    init {
        require(time > MINIMUM_LENGTH) { ERROR_MESSAGE }
    }

    override fun toString() = time.toString() + MINUTE

    companion object {
        const val ERROR_MESSAGE = "상영시간은 존재해야한다."
        const val MINUTE = "분"
        const val MINIMUM_LENGTH = 0
    }
}
