package woowacourse.movie.data.repository

import woowacourse.movie.R
import woowacourse.movie.domain.admodel.Ad
import woowacourse.movie.domain.repository.AdRepository

object AdRepositoryImpl : AdRepository {
    override fun getAds(): List<Ad> {
        return listOf(
            Ad(id = "1", content = "우테코 짱", imageId = R.drawable.ad_img1),
            Ad(id = "2", content = "광고 문의", imageId = R.drawable.ad_img2),
        )
    }
}
