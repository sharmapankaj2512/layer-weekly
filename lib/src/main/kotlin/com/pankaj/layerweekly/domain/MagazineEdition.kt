package com.pankaj.layerweekly.domain

import java.util.*

class MagazineEdition(
    val magazine: Magazine,
    val number: String,
    override val id: Id = Id(UUID.randomUUID()),
) : Aggregate(id) {
    val topics = mutableSetOf<Topic>()

    fun add(topics: Set<String>) {
        topics.filter { it.isNotBlank() }
            .map { title -> Topic(title) }
            .also { ts -> this.topics.addAll(ts) }
            .map { topic -> TopicAdded(topic) }
            .also { raise(it) }
    }

    fun has(topics: Set<Topic>): Boolean {
        return this.topics.containsAll(topics)
    }
}
