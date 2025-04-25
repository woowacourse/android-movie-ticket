package woowacourse.movie.view.movie.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import woowacourse.movie.common.DummyData

@Parcelize
data class AdUiModel(
    @DrawableRes val imageResId: Int = DummyData.adImage,
) : Parcelable
