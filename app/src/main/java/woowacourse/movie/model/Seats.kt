package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class Seats private constructor(
    private val _seats: MutableList<Seat>,
) : Parcelable {
    val value: List<Seat>
        get() = _seats.toList()

    val size: Int
        get() = _seats.size

    val totalPrice: Int
        get() = _seats.sumOf { it.price }

    fun labels(): List<String> = _seats.map { it.label }

    fun contains(label: String): Boolean = _seats.any { it.label == label }

    fun add(label: String): Boolean = _seats.add(Seat(label))

    fun remove(label: String): Boolean = _seats.removeIf { it.label == label }

    companion object {
        fun create(): Seats = Seats(mutableListOf())
    }
}
