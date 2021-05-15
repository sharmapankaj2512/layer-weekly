package com.pankaj.layerweekly.domain

abstract class Aggregate(id: Id) : Entity(id) {
    val events = mutableSetOf<DomainEvent>()

    fun raise(events: List<DomainEvent>) {
        this.events.addAll(events)
    }

    fun raise(event: DomainEvent) {
        this.events.add(event)
    }
}
