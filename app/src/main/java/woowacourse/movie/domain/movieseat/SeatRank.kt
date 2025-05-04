enum class SeatRank(val price: Int) {
    S(15_000),
    A(12_000),
    B(10_000),
    ;

    companion object {
        fun get(row: Int): String =
            when (row) {
                0, 1 -> "B"
                2, 3 -> "S"
                else -> "A"
            }

        fun from(name: String): SeatRank =
            entries.firstOrNull { it.name.equals(name, ignoreCase = true) }
                ?: throw IllegalArgumentException("$name : 해당하는 랭크가 존재하지 않습니다.")
    }
}
