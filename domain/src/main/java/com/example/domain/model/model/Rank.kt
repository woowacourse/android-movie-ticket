package com.example.domain.model.model

import com.example.domain.model.price.Price

enum class Rank(val price: Price) {
    A(Price(12000)), S(Price(15000)), B(Price(10000))
}
