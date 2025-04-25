package woowacourse.movie.domain

import java.io.Serializable

class Points(values: Set<Point> = setOf()) : Serializable {
    private val _points: MutableSet<Point> = values.toMutableSet()
    val points get() = _points.toSet()

    fun has(point: Point): Boolean {
        return _points.contains(point)
    }

    fun totalPrice(): Int {
        return _points.sumOf { it.price() }
    }

    operator fun minus(point: Point) {
        _points.remove(point)
    }

    operator fun plus(point: Point) {
        _points.add(point)
    }
}
