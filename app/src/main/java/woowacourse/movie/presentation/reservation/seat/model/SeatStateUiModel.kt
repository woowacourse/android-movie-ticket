package woowacourse.movie.presentation.reservation.seat.model

import android.annotation.SuppressLint
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import woowacourse.movie.R

@SuppressLint("PrivateResource")
@DrawableRes
private val EMPTY_ICON = com.google.android.material.R.drawable.m3_tabs_transparent_background

enum class SeatStateUiModel(
    @ColorRes val backGroundColor: Int,
    @DrawableRes val iconRes: Int = EMPTY_ICON
) {
    EMPTY(R.color.white),
    SELECT(R.color.yellow, R.drawable.check_24),
    BANNED(R.color.black),
    RESERVED(androidx.appcompat.R.color.material_grey_600, R.drawable.close_24);
}