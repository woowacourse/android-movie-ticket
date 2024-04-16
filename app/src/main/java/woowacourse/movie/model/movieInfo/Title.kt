package woowacourse.movie.model.movieInfo

class Title(private val name: String) {
    init {
        require(name.isNotEmpty()){ "제목의 길이는 1이상이어야 합니다." }
    }
}