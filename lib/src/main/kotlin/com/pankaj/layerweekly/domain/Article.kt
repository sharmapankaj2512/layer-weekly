package com.pankaj.layerweekly.domain

class Article(
    val title: String,
    val content: String,
    private val topic: Topic,
    private val edition: MagazineEdition
) : Aggregate() {

    init {
        require(edition.has(topic)) { "Topic is not available for the given magazine edition" }

        raise(DraftArticleSubmitted(this))
    }

    fun hasEdition(edition: MagazineEdition): Boolean {
        return edition == this.edition
    }

    fun hasTopic(topic: String): Boolean {
        return this.topic.hasTitle(topic)
    }
}
