package woowacourse.movie.model.main

sealed class MainData {
    abstract val mainViewType: MainViewType
    abstract val id: Long
}
