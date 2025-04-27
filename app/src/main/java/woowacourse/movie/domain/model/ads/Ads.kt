package woowacourse.movie.domain.model.ads

class Ads {
    fun count(
        dataCount: Int,
        cycle: Int,
    ): Int {
        return dataCount / cycle
    }
}
