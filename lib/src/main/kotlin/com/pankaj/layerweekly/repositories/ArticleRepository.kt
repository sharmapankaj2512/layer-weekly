package com.pankaj.layerweekly.repositories

import com.pankaj.layerweekly.domain.Article

interface ArticleRepository {
    fun create(article: Article)
}
