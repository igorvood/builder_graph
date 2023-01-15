package ru.vood.builder.graph.traceGraph

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.vood.builder.graph.traceGraph.dto.Topic
import kotlin.test.assertContains
import kotlin.test.assertEquals

class TopicNodeTest {

    @Test
    fun creatingCheckTest(){
        val topic1 = Topic("asd", setOf())
        val assertThrows = assertThrows<IllegalArgumentException> { Topic("asd", setOf(topic1)) }

        println(assertThrows.message)
        assertEquals("end nodes do not fit the restrictions [Topic(name=asd, nextNodes=[])]", assertThrows.message)

    }
}