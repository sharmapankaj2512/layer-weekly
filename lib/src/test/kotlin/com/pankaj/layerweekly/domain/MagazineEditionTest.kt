package com.pankaj.layerweekly.domain

import com.pankaj.layerweekly.builders.MagazineEditionBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MagazineEditionTest {
    @Test
    fun addsValidTopics() {
        val edition = MagazineEditionBuilder(topics = setOf("sports")).build()

        edition.add(setOf("sports"))
        edition.add(setOf("business"))

        assertEquals(edition.topics, setOf(Topic("sports"), Topic("business")))
    }

    @Test
    fun doesNotAddDuplicateTopic() {
        val edition = MagazineEditionBuilder(topics = setOf("sports")).build()

        edition.add(setOf("sports"))
        edition.add(setOf("sports", "business"))

        assertEquals(edition.topics, setOf(Topic("sports"), Topic("business")))
    }

    @Test
    fun ignoresInvalidTopics() {
        val edition = MagazineEditionBuilder(topics = setOf("sports")).build()

        edition.add(setOf("sports"))
        edition.add(setOf(""))
        edition.add(setOf("  "))
        edition.add(setOf())

        assertEquals(edition.topics, setOf(Topic("sports")))
    }
}