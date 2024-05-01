package woowacourse.movie.presentation.screen

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.data.MockMovies
import woowacourse.movie.presentation.screen.adapter.MovieScreenAdapter

@RunWith(AndroidJUnit4::class)
class MovieScreenAdapterTest {
    private lateinit var context: Context
    private lateinit var adapter: MovieScreenAdapter

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        adapter =
            MovieScreenAdapter(
                context, listOf("ad_placeholder"),
                listOf(
                    MockMovies.defaultMovie,
                    MockMovies.defaultMovie,
                    MockMovies.defaultMovie,
                ),
            ) {}
    }

    @Test
    fun `첫_번째_아이템이_올바른_movie를_표시한다`() {
        val viewType = adapter.getItemViewType(0)
        TestCase.assertEquals(0, viewType)
    }

    @Test
    fun `네_번째_아이템이_광고를_표시한다`() {
        val viewType = adapter.getItemViewType(3)
        TestCase.assertEquals(1, viewType)
    }

    @Test
    fun `어댑터에_10개의_영화를_3번_추가하면_총_30개의_영화와_10개의_광고를_가진다`() {
        val repeatedMovies = List(3) { MockMovies.defaultMovie }
        val expectedSize = repeatedMovies.size + repeatedMovies.size / 3
        adapter =
            MovieScreenAdapter(
                context,
                listOf("ad_placeholder"),
                repeatedMovies
            ) {}

        TestCase.assertEquals(expectedSize, adapter.itemCount)
    }
}
