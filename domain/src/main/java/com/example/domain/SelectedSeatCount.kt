package com.example.domain

class SelectedSeatCount {
    var value = 0
        private set

    fun plusSeat() {
        value++
    }

    fun minusSeat() {
        if (value> 0) {
            value--
        }
    }

    fun isMaxCount(maxCount: Int) = value == maxCount
}
