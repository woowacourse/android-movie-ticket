package woowacourse.movie.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class AdbState(
    @DrawableRes
    val imgId: Int,
    val adbDescription: String
) : Parcelable
