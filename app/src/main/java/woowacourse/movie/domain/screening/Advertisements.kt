package woowacourse.movie.domain.screening

class Advertisements(
    private val value: List<Advertisement>,
) {
    private val size = value.size
    private var position = 0

    fun fetchAdvertisement(): Advertisement {
        val advertisement: Advertisement = value[position]
        position = position.plus(1) % size
        return advertisement
    }
}
