package ru.vood.builder.graph.abstraction

import kotlin.reflect.KClass

abstract class AbstractNode(
    override val checkLimitationClassNextNodes: Set<KClass<out INode>>
) : INode {
    abstract fun nextNodes(): Set<INode>
//    abstract fun checkLimitationClassNextNodes(): Set<KClass<out INode>>

    protected fun getBadNodesNodes(): Set<INode> {
        val toSet = nextNodes()
            .filter { nextN -> !checkLimitationClassNextNodes.contains(nextN::class) }
            .toSet()
        return toSet
    }

}

