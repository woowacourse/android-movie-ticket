package domain

@JvmInline
value class SeatRow(val row: Int) {

    fun getGrade() = Grade.checkGrade(row)
}
