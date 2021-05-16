package com.pankaj.layerweekly.builders

import com.pankaj.layerweekly.domain.Article
import com.pankaj.layerweekly.domain.MagazineEdition

data class ArticleBuilder(
    val topics: Set<String> = setOf("science"),
    val title: String = "Manchester City win English Premier League",
    val content: String = "Manchester City were crowned champions of England once again ...",
    val edition: MagazineEdition = MagazineEditionBuilder().build()
) {
    fun build(): Article {
        return Article(title, content, topics, edition)
    }
}