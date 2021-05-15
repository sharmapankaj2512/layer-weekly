package com.pankaj.layerweekly.domain

class MagazineEdition(val magazine: Magazine, val number: String): Aggregate() {
    val topics = mutableSetOf<Topic>()

    fun add(topics: Set<String>) {
        topics.filter { it.isNotBlank() }
            .map { title -> Topic(title) }
            .also { ts -> this.topics.addAll(ts) }
            .map { topic -> TopicAdded(topic) }
            .also { raise(it) }
    }

    fun has(topic: Topic): Boolean {
        return topics.contains(topic)
    }
}
