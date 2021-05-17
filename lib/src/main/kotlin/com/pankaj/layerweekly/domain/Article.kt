package com.pankaj.layerweekly.domain

import com.pankaj.layerweekly.shared.Messages
import java.util.*

class Article(
    val title: String,
    val content: String,
    private val topics: Set<Topic>,
    private val edition: MagazineEdition,
    override val id: Id = Id(UUID.randomUUID()),
) : Aggregate(id) {

    constructor(
        title: String,
        content: String,
        topics: Set<String>,
        edition: MagazineEdition,
    ) : this(
        title = title,
        content = content,
        topics = topics.map { Topic(it) }.toSet(),
        edition = edition
    )

    init {
        require(edition.has(topics)) { Messages.TOPIC_NOT_AVALIABLE_IN_EDITION }

        raise(DraftArticleSubmitted(this))
    }

    fun hasEdition(edition: MagazineEdition): Boolean {
        return edition == this.edition
    }

    fun hasTopic(topic: String): Boolean {
        return this.topics.any { it.hasTitle(topic) }
    }

    fun newChangeRequest(suggestedChange: String): ChangeRequest {
        return ChangeRequest(article = this, content = suggestedChange)
    }
}
