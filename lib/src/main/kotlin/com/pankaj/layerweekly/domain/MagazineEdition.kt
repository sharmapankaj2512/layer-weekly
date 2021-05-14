package com.pankaj.layerweekly.domain

class MagazineEdition(val magazine: Magazine, val number: String): Aggregate() {
    fun add(topics: Set<String>) {
        topics.filter { it.isNotBlank() }
            .map { title -> Topic(title) }
            .also { ts -> this.topics.addAll(ts) }
            .map { topic -> TopicAdded(topic) }
            .also { raise(it) }
    }

    val topics = mutableSetOf<Topic>()
}
