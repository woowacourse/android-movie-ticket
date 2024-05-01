package woowacourse.movie.study

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class CapturingTest {

    enum class Direction { NORTH, SOUTH }
    enum class RecordingOutcome { RECORDED }
    enum class RoadType { HIGHWAY }

    class Car {
        fun recordTelemetry(
            speed: Double,
            direction: Direction,
            roadType: RoadType?
        ): RecordingOutcome {
            throw UnsupportedOperationException("Not implemented")
        }
    }

    @Test
    fun `test`() {
        val car = mockk<Car>()
        // Non-Null 하지 않은 Double 타입의 값을 캡쳐한다!
        val speedSlot = slot<Double>()
        // Nullalble 한거 안들어가는데..? 공식문서가 잘못된것일까??
//        val roadTypeSlot = slot<RoadType?>()
        // Any 의 sub 타입들만 들어간다..
        val roadTypeSlot = slot<RoadType>()
        val list = mutableListOf<Double>()

        every {
            car.recordTelemetry(
                speed = capture(speedSlot), // speed 값을 캡쳐하고, speedSlot 에 저장한다.
                direction = Direction.NORTH,
                roadType = capture(roadTypeSlot),
            )
        } answers { // answers 는 returns 에 비해 동적으로 값을 반환할 수 있음!
            println("Speed: ${list}, roadType: ${roadTypeSlot.captured}") // list [] 빈값 출력
            // speedSlot.captured 는 speedSlot 에 저장된 값을 가져온다.
            // roadTypeSlot.captured 는 roadTypeSlot 에 저장된 값을 가져온다.
            println("Speed: ${speedSlot.captured}, roadType: ${roadTypeSlot.captured}")
            RecordingOutcome.RECORDED
        }
        // when
        car.recordTelemetry(speed = 15.0, direction = Direction.NORTH, RoadType.HIGHWAY)
        car.recordTelemetry(speed = 16.0, direction = Direction.NORTH, RoadType.HIGHWAY)
        // then
        verifyOrder {// 순서대로 호출되는지
            car.recordTelemetry(
                speed = or(15.0, 16.0),
                direction = Direction.NORTH,
                roadType = RoadType.HIGHWAY
            )
            car.recordTelemetry(
                speed = 16.0,
                direction = Direction.NORTH,
                roadType = RoadType.HIGHWAY
            )
        }
        verify(exactly = 2) {
            car.recordTelemetry(
                speed = any(),
                direction = Direction.NORTH,
                roadType = RoadType.HIGHWAY
            )
        }
        // confirmVerified 는 모든 verify 를 확인한다.
        // 만약, 모든 지정하지 않은 verify 가 있으면, confirmVerified 를 호출하면 에러가 발생한다.
        confirmVerified(car)
    }

    @Test
    fun `test2`() {
        val car = mockk<Car>()
        val speedSlot = slot<Double>()
        val roadTypeSlot = slot<RoadType>()

        every {
            car.recordTelemetry(
                speed = capture(speedSlot),
                direction = Direction.NORTH,
                roadType = capture(roadTypeSlot),
            )
        } returns RecordingOutcome.RECORDED
        // when
        car.recordTelemetry(speed = 15.0, direction = Direction.NORTH, RoadType.HIGHWAY)
        car.recordTelemetry(speed = 16.0, direction = Direction.NORTH, RoadType.HIGHWAY)
        // then
        shouldThrow<AssertionError> {
            confirmVerified(car) // 엄격한 친구!
        }
        // recordTelemetry 가 2번 호출되었는데, verify 가 2번 호출되지 않았기 때문에 에러가 발생한다.
        // Verified call count: 0
        // Recorded call count: 2
    }

    @Test
    fun `test3`() {
        val car = mockk<Car>()
        val speedSlot = slot<Double>()
        val roadTypeSlot = slot<RoadType>()

        every {
            car.recordTelemetry(
                speed = capture(speedSlot),
                direction = Direction.NORTH,
                roadType = capture(roadTypeSlot),
            )
        } returns RecordingOutcome.RECORDED
        // when
        car.recordTelemetry(speed = 15.0, direction = Direction.NORTH, RoadType.HIGHWAY)
        car.recordTelemetry(speed = 16.0, direction = Direction.NORTH, RoadType.HIGHWAY)
        // then
        verifyOrder {
            car.recordTelemetry(
                speed = 15.0,
                direction = Direction.NORTH,
                roadType = RoadType.HIGHWAY
            )
            car.recordTelemetry(
                speed = 16.0,
                direction = Direction.NORTH,
                roadType = RoadType.HIGHWAY
            )
        }
        confirmVerified(car)
    }

    @Test
    fun `test4`() {
        val car = mockk<Car>()
        val speedSlot = slot<Double>()
        val roadTypeSlot = slot<RoadType>()

        every {
            car.recordTelemetry(
                speed = capture(speedSlot),
                direction = Direction.NORTH,
                roadType = capture(roadTypeSlot),
            )
        } returns RecordingOutcome.RECORDED
        // when
        car.recordTelemetry(speed = 15.0, direction = Direction.NORTH, RoadType.HIGHWAY)
        // then
        speedSlot.captured shouldBe 15.0
        roadTypeSlot.captured shouldBe RoadType.HIGHWAY
    }
}