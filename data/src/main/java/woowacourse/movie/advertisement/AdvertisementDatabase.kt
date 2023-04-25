package woowacourse.movie.advertisement

object AdvertisementDatabase {
    val advertisements = listOf<AdvertisementEntity>(
        AdvertisementEntity(0, "https://woowacourse.github.io/"),
    )

    fun selectAdvertisement(id: Long): AdvertisementEntity {
        return advertisements.find { it.id == id }
            ?: throw NoSuchElementException()
    }
}
