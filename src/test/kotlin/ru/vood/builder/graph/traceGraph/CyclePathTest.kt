package ru.vood.builder.graph.traceGraph

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
        Arrow(ExternalTopic("t2"), ExternalFlinkService("s1"))
    )

    @Test
    fun creatingWithCycleCheckTest() {

        val cyclicGraph = acyclicGraph.plus(Arrow(ExternalFlinkService("s1"), ExternalTopic("t2")))

        val assertThrows = assertThrows<IllegalStateException> { cyclicGraph.assembly() }

        println(assertThrows.message)
        assertEquals("cycle detected", assertThrows.message)

    }
}
