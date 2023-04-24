package woowacourse.movie.model.main

import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import woowacourse.movie.ui.main.adapter.MainViewType

data class AdvertisementUiModel(
    override val id: Long,
    val link: String,
    @DrawableRes val image: Int,
) : MainData() {
    override val mainViewType: MainViewType = MainViewType.ADVERTISEMENT

    fun getIntent(): Intent {
        return Intent(Intent.ACTION_VIEW, Uri.parse(link))
    }
}
