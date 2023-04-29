package com.example.domain.discountPolicy.policy

import com.example.domain.discountPolicy.apply.Discount
import com.example.domain.discountPolicy.apply.JoJoNightDiscount
import com.example.domain.discountPolicy.apply.MovieDayDiscount
import com.example.domain.discountPolicy.condition.DiscountCondition
import com.example.domain.discountPolicy.condition.JoJoNightCondition
import com.example.domain.discountPolicy.condition.MovieDayCondition

sealed class Policy(val discountCondition: DiscountCondition, val discount: Discount) {
    class JoJoNightPolicy : Policy(JoJoNightCondition(), JoJoNightDiscount())
    class MovieDayPolicy : Policy(MovieDayCondition(), MovieDayDiscount())
}
