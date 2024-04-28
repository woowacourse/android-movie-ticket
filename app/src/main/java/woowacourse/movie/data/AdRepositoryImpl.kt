package woowacourse.movie.data

import woowacourse.movie.domain.repository.AdRepository

class AdRepositoryImpl : AdRepository {
    override fun getAds(): List<String> {
        return MockAds.sampleAds
    }
}
