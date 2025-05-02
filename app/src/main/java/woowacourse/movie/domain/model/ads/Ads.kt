package woowacourse.movie.domain.model.ads

class Ads(
    itemsBeforeAd: Int,
) {
    val insertionInterval = itemsBeforeAd + 1

    fun count(dataCount: Int): Int {
        return dataCount / insertionInterval
    }
}
