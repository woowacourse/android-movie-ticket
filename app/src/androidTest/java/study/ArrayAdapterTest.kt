package study

import android.content.Context
import android.widget.ArrayAdapter
import androidx.test.core.app.ApplicationProvider
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
    fun test1() {
        val list = listOf(1)
        val adapter = ArrayAdapter(context, TEST_RESOURCE_ID, list)
        adapter.add(2)
        adapter.clear()
    }

    @Test
    fun test2() {
        val list = listOf(1, 2)
        val adapter = ArrayAdapter(context, TEST_RESOURCE_ID, list)
        adapter.add(3)
        adapter.clear()
    }

    @Test
    fun test3() {
        val list = listOf(1)
        val clazz = java.util.Collections.singletonList(1)::class.java
        Assertions.assertTrue(clazz.isInstance(list))
    }

    @Test
    fun test4() {
        val list = listOf(1, 2)
        val clazz = java.util.Arrays.asList(1)::class.java
        Assertions.assertTrue(clazz.isInstance(list))
    }

    @Test
    fun test5() {
        val list = listOf(1).toList()
        val clazz = java.util.Collections.singletonList(1)::class.java
        Assertions.assertTrue(clazz.isInstance(list))
    }

    @Test
    fun test6() {
        val list = listOf(1, 2).toList()
        val clazz = java.util.ArrayList<Int>()::class.java
        Assertions.assertTrue(clazz.isInstance(list))
    }

    @Test
    fun test7() {
        val list = listOf(1).toMutableList()
        val adapter = ArrayAdapter(context, TEST_RESOURCE_ID, list)
        adapter.add(2)
        adapter.clear()
    }

    @Test
    fun test8() {
        val list = listOf(1, 2).toMutableList()
        val adapter = ArrayAdapter(context, TEST_RESOURCE_ID, list)
        adapter.add(3)
        adapter.clear()
    }
}
