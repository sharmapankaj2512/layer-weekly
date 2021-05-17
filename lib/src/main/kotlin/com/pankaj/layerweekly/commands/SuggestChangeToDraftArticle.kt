package com.pankaj.layerweekly.commands

import com.pankaj.layerweekly.domain.Id
import com.pankaj.layerweekly.repositories.ArticleRepository
import com.pankaj.layerweekly.repositories.ChangeRequestRepository

class SuggestChangeToDraftArticle(
    private val articleRepository: ArticleRepository,
    private val changeRequestRepository: ChangeRequestRepository
) {
    fun execute(request: SuggestChangeToDraftArticleRequest) {
        articleRepository.find(request.articleId)?.let { article ->
            val changeRequest = article.newChangeRequest(request.suggestedChange)
            changeRequestRepository.create(changeRequest)
        }
    }
}

data class SuggestChangeToDraftArticleRequest(
    val articleId: Id,
    val suggestedChange: String
)
