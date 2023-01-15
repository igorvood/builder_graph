package ru.vood.builder.graph.traceGraph.dto

import ru.vood.builder.graph.abstraction.AbstractNode
import ru.vood.builder.graph.abstraction.IExternalNode
import ru.vood.builder.graph.abstraction.INode
import kotlin.reflect.KClass

data class ExternalFlinkService(
    val name: String,

) : IExternalNode

