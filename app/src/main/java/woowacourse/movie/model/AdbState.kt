package woowacourse.movie.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import woowacourse.movie.ui.main.itemModel.AdbItemModel

@Parcelize
data class AdbState(
    @DrawableRes
    val imgId: Int,
    val adbDescription: String
) : Parcelable {
    fun convertToItemModel(onClick: (position: Int) -> Unit): AdbItemModel {
        return AdbItemModel(this, onClick)
    }
}
