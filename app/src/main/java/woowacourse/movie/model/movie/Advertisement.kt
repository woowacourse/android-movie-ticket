package woowacourse.movie.model.movie

class Advertisement(
    val adId: String,
) {
    companion object {
        const val WOOWA_TECH_BANNER_ID = "woowa_tech_ad1"
        const val JOIN_COUPON_BANNER_ID = "join_coupon_ad1"
        const val NIGHT_SALE_BANNER_ID = "night_sale_ad1"

        fun getDefaultAds(): List<Advertisement> =
            listOf(
                Advertisement(WOOWA_TECH_BANNER_ID),
                Advertisement(JOIN_COUPON_BANNER_ID),
                Advertisement(NIGHT_SALE_BANNER_ID),
            )
    }
}
