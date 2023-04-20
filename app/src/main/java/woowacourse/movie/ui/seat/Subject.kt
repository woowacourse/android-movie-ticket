package woowacourse.movie.ui.seat

import woowacourse.movie.model.SeatPositionState

interface Subject {
    fun registerObserver(observer: Observer) // 옵저버 등록
    fun removeObserver(observer: Observer) // 옵저버 삭제
    fun notifyObserver(
        positionState: List<SeatPositionState>
    ) // 옵저버에게 업데이트 알림
}
