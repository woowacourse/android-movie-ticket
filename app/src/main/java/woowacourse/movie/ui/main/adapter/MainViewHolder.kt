package woowacourse.movie.ui.main.adapter

import android.view.View
import woowacourse.movie.model.main.MainData
import woowacourse.movie.model.main.MainViewType

sealed class MainViewHolder(val view: View) {
    abstract fun onBind(data: MainData)
    abstract val mainViewType: MainViewType
}
