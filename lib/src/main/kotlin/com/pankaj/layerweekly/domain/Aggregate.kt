package com.pankaj.layerweekly.domain

open class Aggregate {
    val events = mutableSetOf<DomainEvent>()

    fun raise(events: List<DomainEvent>) {
        this.events.addAll(events)
    }
}
