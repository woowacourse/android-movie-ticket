package woowacourse.movie.db.advertisement

import woowacourse.movie.model.advertisement.Advertisement

class AdvertisementDao {
    private val advertisements: List<Advertisement> = AdvertisementDatabase.advertisements

    fun findAll(): List<Advertisement> = advertisements
}
