package domain

enum class Grade {
    B, S, A;

    companion object {
        fun checkGrade(position: Int): Grade {
            return when (position) {
                in 0..7 -> B
                in 8..15 -> S
                else -> A
            }
        }
    }
}
