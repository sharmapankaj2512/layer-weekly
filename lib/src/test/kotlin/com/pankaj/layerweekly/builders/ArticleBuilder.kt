package com.pankaj.layerweekly.builders

import com.pankaj.layerweekly.domain.Article
import com.pankaj.layerweekly.domain.MagazineEdition
import com.pankaj.layerweekly.domain.Topic

data class ArticleBuilder(
    val topic: String = "science",
    val title: String = "Manchester City win English Premier League",
    val content: String = "Manchester City were crowned champions of England once again ...",
    val edition: MagazineEdition = MagazineEditionBuilder().build()
) {
    fun build(): Article {
        return Article(title, content, Topic(topic), edition)
    }
}