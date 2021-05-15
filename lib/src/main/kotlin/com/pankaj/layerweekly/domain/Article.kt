package com.pankaj.layerweekly.domain

import com.pankaj.layerweekly.shared.Messages

class Article(
    val title: String,
    val content: String,
    private val topic: Topic,
    private val edition: MagazineEdition
) : Aggregate() {

    init {
        require(edition.has(topic)) { Messages.TOPIC_NOT_AVALIABLE_IN_EDITION }

        raise(DraftArticleSubmitted(this))
    }

    fun hasEdition(edition: MagazineEdition): Boolean {
        return edition == this.edition
    }

    fun hasTopic(topic: String): Boolean {
        return this.topic.hasTitle(topic)
    }
}
