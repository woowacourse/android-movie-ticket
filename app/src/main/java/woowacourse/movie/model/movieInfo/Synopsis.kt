package woowacourse.movie.model.movieInfo

import java.io.Serializable

class Synopsis(val content: String) : Serializable {
    init {
        require(content.isNotEmpty()) { "시놉시스의 길이는 1이상이어야 합니다." }
    }

    override fun toString() = content
}
