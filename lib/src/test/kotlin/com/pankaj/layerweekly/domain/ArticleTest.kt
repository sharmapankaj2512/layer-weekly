package com.pankaj.layerweekly.domain

import com.pankaj.layerweekly.builders.ArticleBuilder
import com.pankaj.layerweekly.builders.MagazineEditionBuilder
import com.pankaj.layerweekly.shared.Messages
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Article")
class ArticleTest {
    @Test
    fun canHaveMultipleTopics() {
        val edition = MagazineEditionBuilder(topics = setOf("sports")).build()
        val article = ArticleBuilder(topics = setOf("sports"), edition = edition).build()

        assertTrue(article.hasTopic("sports"))
    }

    @Test
    fun throwsErrorIfTopicAssignedIsNotAValidMagazineEditionTopic() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Article(
                "Title",
                "Content",
                setOf(Topic("Health")),
                MagazineEditionBuilder(topics = setOf("sports")).build()
            )
        }

        assertEquals(Messages.TOPIC_NOT_AVALIABLE_IN_EDITION, exception.message)
    }
}