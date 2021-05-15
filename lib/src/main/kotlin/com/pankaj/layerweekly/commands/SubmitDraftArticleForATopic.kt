package com.pankaj.layerweekly.commands

import com.pankaj.layerweekly.domain.Article
import com.pankaj.layerweekly.domain.Id
import com.pankaj.layerweekly.domain.Topic
import com.pankaj.layerweekly.repositories.ArticleRepository
import com.pankaj.layerweekly.repositories.MagazineEditionRepository

class SubmitDraftArticleForATopic(
    private val articleRepository: ArticleRepository,
    private val editionRepository: MagazineEditionRepository
) {
    fun execute(request: SubmitDraftArticleForATopicRequest) {
        editionRepository.find(request.id)?.let { edition ->
            val topic = Topic(request.topic)
            val article = Article(request.title, request.content, topic, edition)
            articleRepository.create(article)
        } ?: throw IllegalArgumentException("Invalid magazine edition")
    }
}

data class SubmitDraftArticleForATopicRequest(
    val id: Id,
    val topic: String,
    val title: String,
    val content: String
)
