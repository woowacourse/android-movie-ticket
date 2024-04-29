package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MovieSelectedSeatsTest {
    private lateinit var movieSelectedSeats: MovieSelectedSeats

    @BeforeEach
    fun setUp() {
        movieSelectedSeats = MovieSelectedSeats(3)
    }

    @Test
    fun `기본 좌석 테이블은 5행 4열로 구성되어 있다`() {
        assertThat(movieSelectedSeats.getBaseSeats().size).isEqualTo(5 * 4)
    }

    @Test
    fun `선택된 테이블은 초기에 비어있다`() {
        assertThat(movieSelectedSeats.selectedSeats.isEmpty()).isTrue()
    }

    @Test
    fun `좌석을 선택해서 선택된 테이블에 저장할 수 있다`() {
        val seat = MovieSeat(0, 0)
        movieSelectedSeats.selectSeat(seat)
        assertThat(movieSelectedSeats.selectedSeats.contains(seat)).isTrue()
    }

    @Test
    fun `이미 선택된 좌석을 선택하면 선택된 테이블에서 삭제할 수 있다`() {
        val seat = MovieSeat(0, 0)
        movieSelectedSeats.selectSeat(seat)
        movieSelectedSeats.unSelectSeat(seat)
        assertThat(!movieSelectedSeats.selectedSeats.contains(seat)).isTrue()
    }

    @Test
    fun `이미 선택된 좌석인지 확인할 수 있다`() {
        val seat = MovieSeat(0, 0)
        movieSelectedSeats.selectSeat(seat)
        assertThat(movieSelectedSeats.isSelected(0)).isTrue()
    }

    @Test
    fun `선택된 좌석들의 총 가격을 알 수 있다`() {
        val seat1 = MovieSeat(0, 0)
        val seat2 = MovieSeat(2, 0)
        val seat3 = MovieSeat(4, 0)
        movieSelectedSeats.selectSeat(seat1)
        movieSelectedSeats.selectSeat(seat2)
        movieSelectedSeats.selectSeat(seat3)

        assertThat(movieSelectedSeats.totalPrice()).isEqualTo(37000)
    }

    @Test
    fun `좌석이 모두 선택됐는지 확인 가능하다`() {
        val seat1 = MovieSeat(0, 0)
        val seat2 = MovieSeat(2, 0)
        val seat3 = MovieSeat(4, 0)
        movieSelectedSeats.selectSeat(seat1)
        movieSelectedSeats.selectSeat(seat2)
        movieSelectedSeats.selectSeat(seat3)

        assertThat(movieSelectedSeats.isSelectionComplete()).isTrue()
    }

    @Test
    fun `선택된 좌석들의 Position 값들을 알 수 있다`() {
        val seat1 = MovieSeat(0, 0)
        val seat2 = MovieSeat(2, 0)
        val seat3 = MovieSeat(4, 0)
        movieSelectedSeats.selectSeat(seat1)
        movieSelectedSeats.selectSeat(seat2)
        movieSelectedSeats.selectSeat(seat3)

        assertThat(movieSelectedSeats.getSelectedPositions()).isEqualTo(intArrayOf(0, 8, 16))
    }
}
