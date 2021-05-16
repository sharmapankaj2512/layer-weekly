package com.pankaj.layerweekly.commands

import com.pankaj.layerweekly.domain.Article
import com.pankaj.layerweekly.domain.Id
import com.pankaj.layerweekly.repositories.ArticleRepository
import com.pankaj.layerweekly.repositories.MagazineEditionRepository
import com.pankaj.layerweekly.shared.Messages

class SubmitDraftArticleForATopic(
    private val articleRepository: ArticleRepository,
    private val editionRepository: MagazineEditionRepository
) {
    fun execute(request: SubmitDraftArticleForATopicRequest) {
        editionRepository.find(request.id)?.let { edition ->
            val article = Article(
                title = request.title,
                content = request.content,
                topics = request.topics,
                edition = edition
            )
            articleRepository.create(article)
        } ?: throw IllegalArgumentException(Messages.INVALID_MESSAGE_EDITION)
    }
}

data class SubmitDraftArticleForATopicRequest(
    val id: Id,
    val topics: Set<String>,
    val title: String,
    val content: String
)
