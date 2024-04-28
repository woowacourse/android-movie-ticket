package woowacourse.movie.domain.movie

interface ScreenView {
    val id: Long

    companion object {
        var currentId = 1L
    }
}
