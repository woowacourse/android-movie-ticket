package woowacourse.movie

@JvmInline
value class ImageUrl(val url: String) {
    fun isNone(): Boolean = url.isBlank()

    companion object {
        fun none(): ImageUrl = ImageUrl("")
    }
}