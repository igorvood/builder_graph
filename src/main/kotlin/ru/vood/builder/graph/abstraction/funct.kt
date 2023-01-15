package ru.vood.builder.graph.abstraction

inline fun <reified E : Arrow<out IExternalNode, out IExternalNode>> Set<E>.assembly(): Map<IExternalNode, Set<IExternalNode>> {

    return addArrows(mapOf(), this)

}

tailrec  fun < E : Arrow<out IExternalNode, out IExternalNode>> addArrows(
    endedArrow: Map<IExternalNode, Set<IExternalNode>>,
    remainingElements: Set<E>
): Map<IExternalNode, Set<IExternalNode>> {

    if (remainingElements.isEmpty()) return endedArrow

    val fromNodes = endedArrow.values.flatten().toSet()
    val begOnly = if (fromNodes.isNotEmpty())
        remainingElements.filter { fromNodes.contains(it.from) }
    else {
        remainingElements
            .filter { from -> remainingElements.none { to -> from.from == to.to } }
    }

    val nextArrows = begOnly
        .groupBy { it.from }
        .map { it.key to it.value.map { s -> s.to }.toSet() }
        .toMap()

    val allNodes = nextArrows.values.flatten()

    val forCycle = allNodes.filter { an -> endedArrow.allNodes().contains(an) }.toSet()

    if (forCycle.isNotEmpty()) {

        error("cycle detected")
    }

    val remainingElementsNew = remainingElements.minus(begOnly.toSet())

    val nextArrowsVal = nextArrows.plus(endedArrow)
    return addArrows(nextArrowsVal, remainingElementsNew)
}

private fun <K : IExternalNode, V : Set<IExternalNode>> Map<K, V>.allNodes(): Set<IExternalNode> {
    return this.values.flatten().plus(this.keys).toSet()
}

fun <E : Arrow<out IExternalNode, out IExternalNode>> cyclePath(
    arrows: Set<E>,
    path: Set<List<IExternalNode>>
): Set<List<IExternalNode>> {


    val sdf = if (path.isEmpty()) {
        val filter = arrows.filter { dep -> dep.from == arrows.first().from }.toSet()
        val map = filter.map { listOf(it.from, it.to) }.toSet()
        val minus = arrows.minus(filter)
        cyclePath(minus, map)
    } else {

        TODO("Not yet implemented")
    }

    return sdf
}

fun <E : Arrow<out IExternalNode, out IExternalNode>> Set<E>.isCycle(): Boolean {

    val filter = this.filter { dep -> dep.from == this.first().from }

    val cyclePath = cyclePath(this, setOf<List<IExternalNode>>())

    TODO("Not yet implemented")
}