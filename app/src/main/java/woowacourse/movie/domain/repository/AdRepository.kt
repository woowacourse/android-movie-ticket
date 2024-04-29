package woowacourse.movie.domain.repository

import woowacourse.movie.domain.admodel.Ad

interface AdRepository {
    fun getAds(): List<Ad>
}
