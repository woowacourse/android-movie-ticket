package com.example.domain.model.model

import com.example.domain.model.price.MoviePrice

enum class Rank(val price: MoviePrice) {
    A(MoviePrice(12000)), S(MoviePrice(15000)), B(MoviePrice(10000))
}
