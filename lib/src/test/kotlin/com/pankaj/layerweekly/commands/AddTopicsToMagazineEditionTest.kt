package com.pankaj.layerweekly.commands

import com.pankaj.layerweekly.builders.MagazineBuilder
import com.pankaj.layerweekly.builders.MagazineEditionBuilder
import com.pankaj.layerweekly.domain.MagazineEdition
import com.pankaj.layerweekly.domain.Topic
import com.pankaj.layerweekly.domain.TopicAdded
import com.pankaj.layerweekly.repositories.MagazineEditionRepository
import com.pankaj.layerweekly.shared.Messages
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Nested
class AddTopicsToMagazineEditionTest {
    val magazineEditionRepository = mockk<MagazineEditionRepository>()
    val command = AddTopicsToMagazineEdition(magazineEditionRepository)

    @Nested
    inner class GivenLastWeeklyFirstEdition {
        private val magazine = MagazineBuilder().build()
        private val edition = MagazineEditionBuilder(magazine = magazine).build()

        @BeforeEach
        fun beforeEach() {
            every { magazineEditionRepository.find(magazine.id, edition.number) } returns edition
        }

        @Nested
        inner class WhenTopicsAreAdded {
            private val topics = setOf("Cricket", "Business")
            private val capturedEdition = slot<MagazineEdition>()

            @BeforeEach
            fun beforeEach() {
                every { magazineEditionRepository.update(capture(capturedEdition)) } just runs

                command.execute(
                    AddTopicsToMagazineEditionRequest(
                        id = magazine.id,
                        number = edition.number,
                        topics = topics
                    )
                )
            }

            @Test
            fun thenTheTopicsAreLinkedToTheEdition() {
                val expected = setOf(Topic("cricket"), Topic("business"))
                val actual = capturedEdition.captured.topics

                assertEquals(expected, actual)
            }

            @Test
            fun thenTopicsAddedDomainEventsAreRaised() {
                val actual = capturedEdition.captured.events
                val expected = setOf(
                    TopicAdded(topic = Topic("cricket")),
                    TopicAdded(topic = Topic("business"))
                )

                assertEquals(expected, actual)
            }
        }

        @Nested
        inner class WhenTopicsAreAddedToSecondEditionWhichDoesNotExistYet {

            @BeforeEach
            fun beforeEach() {
                every { magazineEditionRepository.find(magazine.id, "2") } returns null
            }

            @Test
            fun thenAnErrorIsReported() {
                val exception = assertThrows(IllegalArgumentException::class.java) {
                    command.execute(
                        AddTopicsToMagazineEditionRequest(
                            magazine.id,
                            "2",
                            setOf("sports")
                        )
                    )
                }
                assertEquals(exception.message, Messages.MAGAZINE_DOES_NOT_EXIST)
            }
        }
    }
}