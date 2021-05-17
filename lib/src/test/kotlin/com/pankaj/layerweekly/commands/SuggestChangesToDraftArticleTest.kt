package com.pankaj.layerweekly.commands

import com.pankaj.layerweekly.builders.ArticleBuilder
import com.pankaj.layerweekly.domain.ChangeRequest
import com.pankaj.layerweekly.repositories.ArticleRepository
import com.pankaj.layerweekly.repositories.ChangeRequestRepository
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Nested
class SuggestChangeToDraftArticleTest {
    private val articleRepository = mockk<ArticleRepository>()
    private val changeRequestRepository = mockk<ChangeRequestRepository>()
    private val command = SuggestChangeToDraftArticle(articleRepository, changeRequestRepository)

    @Nested
    inner class GivenDraftArticle {
        val article = ArticleBuilder().build()

        @BeforeEach
        fun beforeEach() {
            every { articleRepository.find(article.id) } returns article
        }

        @Nested
        inner class WhenAChangeRequestIsSubmitted {
            private val changeRequestCaptured = slot<ChangeRequest>()
            private val suggestedChange = "How about changing the font?"

            @BeforeEach
            fun beforeEach() {
                every { changeRequestRepository.create(capture(changeRequestCaptured)) } just runs

                command.execute(
                    SuggestChangeToDraftArticleRequest(
                        article.id,
                        suggestedChange
                    )
                )
            }

            @Test
            fun thenTheChangeRequestIsAssociatedWithTheDraftArticle() {
                val actual = changeRequestCaptured.captured

                assertEquals(suggestedChange, actual.content)
                assertEquals(article, actual.article)
            }

            @Test
            fun thenChangeSuggestedEventIsRaised() {
                val actual = changeRequestCaptured.captured

                assertEquals(setOf(ChangeSuggested(actual)), actual.events)
            }
        }
    }
}