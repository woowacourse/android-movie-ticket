package woowacourse.movie.common.ui

import android.content.res.Resources

/// 화면의 density를 가져옵니다. (e. 2.0 == 320dpi / 160dpi)
val density = Resources.getSystem().displayMetrics.density

val Int.dp get(): Int = (density * this).toInt()

fun Int.dpToPx(): Float = (this * density)

val Int.sp get(): Float = this / density

// DPI: Dots Per Inch
// DIP : Density Independent Pixel , 일명 DP
// PX: 화면 픽셀 (물리적인 단위)
// SP: Scale Independent Pixel, 텍스트 크기를 지정할 때 사용
// 160dpi(1인치에 160개의 dots=pixel) 기준 으로 1dp = 1px

// 220dpi 기준으로 1dp = 1.375px (= 220/160)