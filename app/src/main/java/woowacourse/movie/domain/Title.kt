package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Title(
    val value: String
) : Parcelable {
    init {
        require(value.isNotEmpty()) { ERROR_EMPTY_MOVIE_TITLE }
        require(value.isNotBlank()) { ERROR_BLANK_MOVIE_TITLE }
    }

    companion object {
        private const val ERROR_EMPTY_MOVIE_TITLE = "영화 타이틀에 빈 글자는 올 수 없습니다."
        private const val ERROR_BLANK_MOVIE_TITLE = "영화 타이틀에 공백이 올 수 없습니다."

    }
}
