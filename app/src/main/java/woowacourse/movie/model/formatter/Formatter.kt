package woowacourse.movie.model.formatter

abstract class Formatter<T> {
    protected abstract val formatString: String
    abstract fun format(data: T): String
}
