package woowacourse.movie.model.movieInfo

import java.io.Serializable

class Title(val name: String) : Serializable {
    init {
        require(name.isNotEmpty()) { "제목의 길이는 1이상이어야 합니다." }
    }

    override fun toString() = name
}
