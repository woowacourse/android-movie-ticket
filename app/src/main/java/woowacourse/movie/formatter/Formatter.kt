package woowacourse.movie.formatter

abstract class Formatter<T> {
    protected abstract val formatString: String
    abstract fun formatToString(data: T): String
    abstract fun formatToOriginal(string: String): T
}
