package com.pankaj.layerweekly.domain

import com.pankaj.layerweekly.builders.MagazineEditionBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class ArticleTest {
    @Test
    fun throwsErrorIfTopicAssignedIsNotAValidMagazineEditionTopic() {
        val message = "Topic is not available for the given magazine edition"

        val exception = assertThrows(IllegalArgumentException::class.java) {
            Article(
                "Title",
                "Content",
                Topic("Health"),
                MagazineEditionBuilder(topics = setOf("sports")).build()
            )
        }

        assertEquals(message, exception.message)
    }
}