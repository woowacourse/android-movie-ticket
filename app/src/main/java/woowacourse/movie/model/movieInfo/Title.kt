package woowacourse.movie.model.movieInfo

import java.io.Serializable

class Title(val content: String) : Serializable {
    init {
        require(content.isNotEmpty()) { "제목의 길이는 1이상이어야 합니다." }
    }
}
