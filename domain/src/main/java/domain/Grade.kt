package domain

enum class Grade {
    B, S, A;

    companion object {
        fun checkGrade(position: Position): Grade {
            return when (position.row) {
                1, 2 -> B
                3, 4 -> S
                else -> A
            }
        }
    }
}
