package woowacourse.movie.model.movieInfo

import java.io.Serializable

class RunningTime(private val time: Int): Serializable {
    init {
        require(time>0){ "상영시간은 존재해야한다." }
    }
}