package com.pankaj.layerweekly.commands

import com.pankaj.layerweekly.domain.Article
import com.pankaj.layerweekly.domain.Id
import com.pankaj.layerweekly.domain.Topic
import com.pankaj.layerweekly.repositories.ArticleRepository
import com.pankaj.layerweekly.repositories.MagazineEditionRepository
import com.pankaj.layerweekly.shared.Messages

class SubmitDraftArticleForATopic(
    private val articleRepository: ArticleRepository,
    private val editionRepository: MagazineEditionRepository
) {
    fun execute(request: SubmitDraftArticleForATopicRequest) {
        editionRepository.find(request.id)?.let { edition ->
            val topic = Topic(request.topic)
            val article = Article(
                title = request.title,
                content = request.content,
                topic = topic,
                edition = edition)
            articleRepository.create(article)
        } ?: throw IllegalArgumentException(Messages.INVALID_MESSAGE_EDITION)
    }
}

data class SubmitDraftArticleForATopicRequest(
    val id: Id,
    val topic: String,
    val title: String,
    val content: String
)
