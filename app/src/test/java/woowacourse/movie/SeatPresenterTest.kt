//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.Assertions.assertFalse
//import org.junit.jupiter.api.Assertions.assertTrue
//import woowacourse.movie.model.theater.Seat
//
//class SeatPresenterTest {
//
//    @Test
//    fun `빈 좌석을 선택 하면 선택된 좌석으로 상태를 변경 한다`() {
//        val seat = Seat('A', 1, "B등급")
//        assertFalse(seat.chosen) // 초기 상태는 선택되지 않은 상태
//        seat.chooseSeat() // 좌석 선택
//        assertTrue(seat.chosen) // 상태 변경 확인
//    }
//
//    @Test
//    fun `선택된 좌석을 선택 하면 빈 좌석으로 상태를 변경 한다`() {
//        val seat = Seat('A', 1, "B등급")
//        seat.chooseSeat() // 좌석을 선택한 상태
//        assertTrue(seat.chosen) // 선택된 상태 확인
//        seat.chooseSeat() // 좌석 선택 취소
//        assertFalse(seat.chosen) // 상태 변경 확인
//    }
//
//    @Test
//    fun `하단에 선택한 좌석 수를 반영한 최종 가격이 표시 된다`() {
//        val seat1 = Seat('A', 1, "B등급")
//        val seat2 = Seat('A', 2, "B등급")
//        seat1.chooseSeat() // 첫 번째 좌석 선택
//        seat2.chooseSeat() // 두 번째 좌석 선택
//        val totalCost = if (seat1.chosen) seat1.price else 0 + if (seat2.chosen) seat2.price else 0
//        val expectedCost = seat1.price + seat2.price
//        assert(totalCost == expectedCost) // 최종 가격 확인
//    }
//}
