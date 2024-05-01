package woowacourse.movie.study

import android.content.Context
import android.widget.ArrayAdapter
import androidx.test.core.app.ApplicationProvider
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

private const val TEST_RESOURCE_ID: Int = 1

/**
 * @see [reference1][https://stackoverflow.com/questions/3200551]
 * @see [reference2][https://stackoverflow.com/questions/59695034]
 * @see [reference3][https://stackoverflow.com/questions/2965747]
 */
class ArrayAdapterTest {
    private lateinit var context: Context

    @BeforeEach
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun `List에_add와_clear를_하면_에러가_발생한다`() {
        val list = listOf(1)
        val adapter = ArrayAdapter(context, TEST_RESOURCE_ID, list)
        assertThrows<UnsupportedOperationException> {
            adapter.add(2)
            adapter.clear()
        }
    }

    @Test
    fun `List에_add와_clear를_하면_에러가_발생한다2`() {
        val list = listOf(1, 2)
        val adapter = ArrayAdapter(context, TEST_RESOURCE_ID, list)
        assertThrows<UnsupportedOperationException> {
            adapter.add(3)
            adapter.clear()
        }
    }

    @Test
    fun `코틀린의_list는_자바의_singletonList의_instance다`() {
        val list = listOf(1)
        val clazz = java.util.Collections.singletonList(1)::class.java
        Assertions.assertTrue(clazz.isInstance(list))
    }

    @Test
    fun `코틀린의_list는_자바의_asList의_instance다`() {
        val list = listOf(1, 2)
        val clazz = java.util.Arrays.asList(1)::class.java
        Assertions.assertTrue(clazz.isInstance(list))
    }

    @Test
    fun `toList를_통해_생성된_객체는_자바의_asList_instace에_속한다`() {
        val list = listOf(1).toList()
        val clazz = java.util.Collections.singletonList(1)::class.java
        Assertions.assertTrue(clazz.isInstance(list))
    }

    @Test
    fun `toList를_통해_생성된_객체는_자바의_ArrayList_instace에_속한다`() {
        val list = listOf(1, 2).toList()
        val clazz = java.util.ArrayList<Int>()::class.java
        Assertions.assertTrue(clazz.isInstance(list))
    }

    @Test
    fun `toMutableList를_통해_생성된_객체를_넣어주면_add를_할_때_에러가_발생하지_않는다`() {
        val list = listOf(1).toMutableList()
        val adapter = ArrayAdapter(context, TEST_RESOURCE_ID, list)
        assertDoesNotThrow {
            adapter.add(2)
            adapter.clear()
        }
    }

    @Test
    fun `toMutableList를_통해_생성된_객체를_넣어주면_add를_할_때_에러가_발생하지_않는다2`() {
        val list = listOf(1, 2).toMutableList()
        val adapter = ArrayAdapter(context, TEST_RESOURCE_ID, list)
        assertDoesNotThrow {
            adapter.add(3)
            adapter.clear()
        }
    }
}
