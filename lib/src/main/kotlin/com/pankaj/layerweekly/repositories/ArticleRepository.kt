package com.pankaj.layerweekly.repositories

import com.pankaj.layerweekly.domain.Article
import com.pankaj.layerweekly.domain.Id

interface ArticleRepository {
    fun create(article: Article)
    fun find(articleId: Id): Article?
}
