package woowacourse.movie.item

import androidx.annotation.DrawableRes
import woowacourse.movie.R

class AdvertisingItem(
    @DrawableRes val advertisingImage: Int = R.drawable.image_advertising,
    override val itemType: ItemType = ItemType.ADVERTISING
) : ModelItem
