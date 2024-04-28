package woowacourse.movie.db

import woowacourse.movie.model.Advertisement

class AdvertisementDao {
    private val advertisements: List<Advertisement> = AdvertisementDatabase.advertisements

    fun findAll(): List<Advertisement> = advertisements
}
