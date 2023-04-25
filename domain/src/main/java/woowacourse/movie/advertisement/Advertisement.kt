package woowacourse.movie.advertisement

data class Advertisement(
    val id: Long,
    val link: String,
) {
    companion object {
        const val CYCLE = 3
    }
}
