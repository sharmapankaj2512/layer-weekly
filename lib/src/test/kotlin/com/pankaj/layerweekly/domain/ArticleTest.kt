package com.pankaj.layerweekly.domain

import com.pankaj.layerweekly.builders.MagazineEditionBuilder
import com.pankaj.layerweekly.shared.Messages
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Article")
class ArticleTest {
    @Test
    fun throwsErrorIfTopicAssignedIsNotAValidMagazineEditionTopic() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Article(
                "Title",
                "Content",
                Topic("Health"),
                MagazineEditionBuilder(topics = setOf("sports")).build()
            )
        }

        assertEquals(Messages.TOPIC_NOT_AVALIABLE_IN_EDITION, exception.message)
    }
}