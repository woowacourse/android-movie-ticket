package woowacourse.movie.model

@JvmInline
value class Count(val number: Int) {
    init {
        require(number >= 1) { "개수는 1 이상이어야 합니다." }
    }

    operator fun inc(): Count {
        return Count(number + 1)
    }

    operator fun dec(): Count  {
        return Count(number - 1)
    }
}
