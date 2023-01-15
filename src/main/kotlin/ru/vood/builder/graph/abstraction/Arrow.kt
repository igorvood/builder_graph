package ru.vood.builder.graph.abstraction

data class Arrow<F : IExternalNode, T : IExternalNode>(
    val from: F,
    val to: T
)

