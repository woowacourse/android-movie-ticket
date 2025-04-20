package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Title(
    val value: String
) : Parcelable {
    init {
        require(value.isNotEmpty()) { "영화 타이틀에 빈 글자는 올 수 없습니다." }
        require(value.isNotBlank()) { "영화 타이틀에 공백이 올 수 없습니다." }
    }
}
