package woowacourse.movie.formatter

abstract class Formatter<T> {
    abstract fun formatToString(data: T, format: String): String
    abstract fun formatToOriginal(string: String, format: String): T
}
