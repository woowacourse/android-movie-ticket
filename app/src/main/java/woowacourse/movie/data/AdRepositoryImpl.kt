package woowacourse.movie.data

import woowacourse.movie.domain.repository.AdRepository

class AdRepositoryImpl : AdRepository {
    override fun getRandomAd(): String {
        return MockAds.sampleAds.random()
    }
}
