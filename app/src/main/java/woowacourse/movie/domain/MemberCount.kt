package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class MemberCount(
    val value: Int
) : Parcelable {
    init {
        require(value >= 1) {"영화 예매 수는 1명이상이어야합니다."}
    }
}