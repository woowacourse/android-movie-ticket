package woowacourse.movie.data.utils

object IdGenerator {
    private var currentId = 0

    fun generateId(): Int {
        return ++currentId
    }
}
