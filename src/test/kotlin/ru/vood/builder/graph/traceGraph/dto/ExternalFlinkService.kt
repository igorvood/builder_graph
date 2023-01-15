package ru.vood.builder.graph.traceGraph.dto

import ru.vood.builder.graph.abstraction.IExternalNode

data class ExternalFlinkService(
    val name: String,

    ) : IExternalNode

