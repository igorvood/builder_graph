package ru.vood.builder.graph.traceGraph

import arrow.core.some
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.vood.builder.graph.abstraction.Arrow
import ru.vood.builder.graph.abstraction.IExternalNode
import ru.vood.builder.graph.abstraction.assembly
import ru.vood.builder.graph.traceGraph.dto.ExternalFlinkService
import ru.vood.builder.graph.traceGraph.dto.ExternalTopic
import kotlin.test.assertEquals

class CyclePathTest {

    val acyclicGraph: Set<Arrow<out IExternalNode, out IExternalNode>> = setOf(
        Arrow(ExternalTopic("t0"), ExternalFlinkService("s1")),
        Arrow(ExternalTopic("t1"), ExternalFlinkService("s1")),
        Arrow(ExternalTopic("t2"), ExternalFlinkService("s3")),
        Arrow(ExternalTopic("t2"), ExternalFlinkService("s4")),
        Arrow(ExternalFlinkService("s1"), ExternalTopic("t2"))
    )

    @Test
    fun creatingWithCycleCheckTest() {

        val cyclicGraph = acyclicGraph.plus(Arrow(ExternalTopic("t2"), ExternalFlinkService("s1")))
        val assertThrows = assertThrows<IllegalStateException> { cyclicGraph.assembly() }
        println(assertThrows.message)
        assertEquals("cycle detected", assertThrows.message)
    }

    @Test
    fun creatingNoCycleCheckTest() {
        val assembly = acyclicGraph.assembly()

        assertEquals(4, assembly.size)
        assertEquals(2, assembly[ExternalTopic(name="t2")]!!.size)
        assertEquals(1, assembly[ExternalTopic(name="t0")]!!.size)
        assertEquals(1, assembly[ExternalTopic(name="t1")]!!.size)
        assertEquals(1, assembly[ExternalFlinkService(name="s1")]!!.size)
    }

}
