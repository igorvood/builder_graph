package ru.vood.builder.graph.traceGraph.dto

import ru.vood.builder.graph.abstraction.AbstractNode
import ru.vood.builder.graph.abstraction.INode
import kotlin.reflect.KClass

data class FlinkService(
    val name: String,
    val nextNodes: Set<INode>,
) : AbstractNode() {
    override fun nextNodes(): Set<INode> {
        return nextNodes
    }

    override fun checkLimitationClassNextNodes(): Set<KClass<out INode>> {
        return setOf(Topic::class)
    }

    init {
        val badNodesNodes = getBadNodesNodes()
        require(badNodesNodes.isNotEmpty()) { "end nodes do not fit the restrictions $badNodesNodes" }
    }
}

