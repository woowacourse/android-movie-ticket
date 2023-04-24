package woowacourse.movie.ui.main.adapter.listview

import android.view.View
import woowacourse.movie.model.main.MainData
import woowacourse.movie.ui.main.adapter.MainViewType

sealed class MainViewHolder(val view: View) {
    abstract fun onBind(data: MainData)
    abstract val mainViewType: MainViewType
}
