package woowacourse.movie.model.movieInfo

class Synopsis(private val content: String) {
    init {
        require(content.isNotEmpty()){ "시놉시스의 길이는 1이상이어야 합니다." }
    }
}