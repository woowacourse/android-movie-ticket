package domain.movie

@JvmInline
value class Name(
    val value: String
) {
    init {
        require(value.length >= MINIMUM_LENGTH)
    }

    companion object {
        private const val MINIMUM_LENGTH = 1
    }
}
