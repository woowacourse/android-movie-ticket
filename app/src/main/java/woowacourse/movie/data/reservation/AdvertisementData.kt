package woowacourse.movie.data.reservation

import woowacourse.movie.domain.reservation.Advertisement
import woowacourse.movie.domain.reservation.Advertisements

interface AdvertisementData {
    val value: Advertisements
}

class LocalAdvertisementData : AdvertisementData {
    override val value: Advertisements =
        Advertisements(
            listOf(
                Advertisement(0, "우아한테크코스"),
            ),
        )
}
