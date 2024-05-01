package woowacourse.movie.model.movie

class Title(val content: String) {
    init {
        require(content.isNotEmpty()) { "제목의 길이는 1이상이어야 합니다." }
    }
}
