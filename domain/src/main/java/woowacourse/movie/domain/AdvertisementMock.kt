package woowacourse.movie.domain

object AdvertisementMock {
    fun createAdvertisement(): Advertisement = Advertisement(
        Image(4)
    )

    fun createAdvertisements(): List<Advertisement> = listOf(
        Advertisement(
            Image(4)
        )
    )
}
