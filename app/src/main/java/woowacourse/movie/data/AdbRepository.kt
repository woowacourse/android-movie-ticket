package woowacourse.movie.data

import woowacourse.movie.R
import woowacourse.movie.model.AdbState

object AdbRepository {
    fun allAdb(): List<AdbState> = adbs.toList()

    private val adbs: List<AdbState> = List(40) {
        AdbState(R.drawable.adv_wooteco)
    }
}
