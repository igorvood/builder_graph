package ru.vood.builder.graph.traceGraph.dto

import ru.vood.builder.graph.abstraction.AbstractNode
import ru.vood.builder.graph.abstraction.INode

data class Topic(
    val name: String,
    override val nextNodes: Set<INode>,
) : AbstractNode(setOf(FlinkService::class)) {
    override fun nextNodes(): Set<INode> {
        return nextNodes
    }


    init {
        val badNodesNodes = getBadNodesNodes()
        require(badNodesNodes.isEmpty()) { "end nodes do not fit the restrictions $badNodesNodes" }
    }
}
