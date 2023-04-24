package woowacourse.movie.model.main

import woowacourse.movie.ui.main.adapter.MainViewType

sealed class MainData {
    abstract val mainViewType: MainViewType
    abstract val id: Long
}
