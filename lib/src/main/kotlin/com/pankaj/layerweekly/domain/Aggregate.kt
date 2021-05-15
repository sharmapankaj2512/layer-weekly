package com.pankaj.layerweekly.domain

import java.util.*

interface Entity {
    fun id(): Id
}

open class Aggregate : Entity {
    private val id = Id(UUID.randomUUID())
    val events = mutableSetOf<DomainEvent>()

    fun raise(events: List<DomainEvent>) {
        this.events.addAll(events)
    }

    fun raise(event: DomainEvent) {
        this.events.add(event)
    }

    override fun id(): Id {
        return id
    }
}
