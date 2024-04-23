package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * 학습 테스트
 */
class ImageTest {
    @Test
    fun `StringImg가 Img 를 상속받고 있다`() {
        val stringImg = StringImage(imageSource = "stringImgFormat")

        assertThat(stringImg).isInstanceOf(Image::class.java)
    }

    @Test
    fun `DrawableImg 가 Image 를 상속받고 있다`() {
        val drawableImg = DrawableImage(imageSource = 1)

        assertThat(drawableImg).isInstanceOf(Image::class.java)
    }
}
