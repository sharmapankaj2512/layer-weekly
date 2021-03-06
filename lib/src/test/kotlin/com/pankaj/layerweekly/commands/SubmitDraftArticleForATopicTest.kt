package com.pankaj.layerweekly.commands

import com.pankaj.layerweekly.builders.MagazineEditionBuilder
import com.pankaj.layerweekly.domain.Article
import com.pankaj.layerweekly.domain.DraftArticleSubmitted
import com.pankaj.layerweekly.domain.Id
import com.pankaj.layerweekly.repositories.ArticleRepository
import com.pankaj.layerweekly.repositories.MagazineEditionRepository
import com.pankaj.layerweekly.shared.Messages
import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*

@Nested
class SubmitDraftArticleForATopicTest {
    private val articleRepository = mockk<ArticleRepository>()
    private val editionRepository = mockk<MagazineEditionRepository>()
    private val command = SubmitDraftArticleForATopic(articleRepository, editionRepository)

    @Nested
    inner class GivenLayerWeeklyMagazineFirstEdition {
        val edition = MagazineEditionBuilder(topics = setOf("sports")).build()

        @BeforeEach
        fun beforeEach() {
            every { editionRepository.find(edition.id) } returns edition
        }

        @Nested
        inner class WithSportsTopic {

            @BeforeEach
            fun beforeEach() {
                edition.add(setOf("sports"))
            }

            @Nested
            inner class WhenADraftArticleForTheSportsTopicIsSubmitted {
                private val topics = setOf("sports")
                private val title = "Manchester City win English Premier League"
                private val content = "Manchester City were crowned champions of England once again ..."
                private val articleCaptured = slot<Article>()

                @BeforeEach
                fun beforeEach() {
                    every { articleRepository.create(capture(articleCaptured)) } just runs

                    command.execute(
                        SubmitDraftArticleForATopicRequest(
                            edition.id,
                            topics,
                            title,
                            content
                        )
                    )
                }

                @Test
                fun thenTheArticleIsCreatedAsDraft() {
                    val actual = articleCaptured.captured

                    assertEquals(title, actual.title)
                    assertEquals(content, actual.content)
                    assertTrue(actual.hasEdition(edition))
                    assertTrue(actual.hasTopic(topics.first()))
                }

                @Test
                fun thenDraftArticleSubmittedForTopicEventIsPublished() {
                    val actual = articleCaptured.captured

                    assertEquals(actual.events, setOf(DraftArticleSubmitted(actual)))
                }
            }

            @Nested
            inner class WhenADraftArticleForNonExistingBusinessTopicIsSubmitted {
                private val topics = setOf("science")
                private val title = "Manchester City win English Premier League"
                private val content = "Manchester City were crowned champions of England once again ..."

                @Test
                fun thenReportError() {
                    val exception = assertThrows(IllegalArgumentException::class.java) {
                        command.execute(
                            SubmitDraftArticleForATopicRequest(
                                edition.id,
                                topics,
                                title,
                                content
                            )
                        )
                    }
                    assertEquals(Messages.TOPIC_NOT_AVALIABLE_IN_EDITION, exception.message)
                }
            }

            @Nested
            inner class WhenADraftArticleForNonExistingSecondEditionIsSubmitted {
                private val topics = setOf("science")
                private val title = "Manchester City win English Premier League"
                private val content = "Manchester City were crowned champions of England once again ..."

                @BeforeEach
                fun beforeEach() {
                    every { editionRepository.find(any()) } returns null
                }

                @Test
                fun thenReportError() {
                    val exception = assertThrows(IllegalArgumentException::class.java) {
                        command.execute(
                            SubmitDraftArticleForATopicRequest(
                                Id(UUID.randomUUID()),
                                topics,
                                title,
                                content
                            )
                        )
                    }
                    assertEquals(Messages.INVALID_MESSAGE_EDITION, exception.message)
                }
            }
        }
    }
}