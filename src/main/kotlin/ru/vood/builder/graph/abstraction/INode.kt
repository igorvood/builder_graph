package ru.vood.builder.graph.abstraction

import kotlin.reflect.KClass
interface INode {
    val nextNodes: Set<INode>

    val checkLimitationClassNextNodes: Set<KClass<out INode>>
}