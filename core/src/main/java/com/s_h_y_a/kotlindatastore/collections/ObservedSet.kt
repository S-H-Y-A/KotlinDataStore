@file:Suppress("unused")

package com.s_h_y_a.kotlindatastore.collections

class ObservedSet<t>(private val set: MutableSet<t> = mutableSetOf()): MutableSet<t> by set {
    internal var notice: (Set<t>) -> Unit = {}
    override fun add(element: t) = set.add(element).update()
    fun addAll(builder: (MutableSet<t>) -> Set<t>) = (set != builder(LinkedHashSet(set))).update()
    override fun addAll(elements: Collection<t>) = set.addAll(elements).update()
    override fun clear() {
        set.clear()
        notice(set)
    }
    override fun remove(element: t) = set.remove(element).update()
    override fun removeAll(elements: Collection<t>) = set.removeAll(elements).update()
    override fun retainAll(elements: Collection<t>) = set.retainAll(elements).update()
    private fun Boolean.update(): Boolean {
        if (this) notice(set)
        return this
    }
}