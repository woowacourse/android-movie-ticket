package woowacourse.movie.model

import woowacourse.movie.R

class MockAdvertisementGenerator {

    fun generate() = Advertisement(
        adImageSrc = R.drawable.image_advertisement,
        url = "https://techcourse.woowahan.com/"
    )
}
