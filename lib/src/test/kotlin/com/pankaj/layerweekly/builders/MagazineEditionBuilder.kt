package com.pankaj.layerweekly.builders

import com.pankaj.layerweekly.domain.Magazine
import com.pankaj.layerweekly.domain.MagazineEdition

data class MagazineEditionBuilder(
    val magazine: Magazine = MagazineBuilder().build(),
    val number: String = "1",
    val topics: Set<String> = setOf()
) {

    fun build(): MagazineEdition {
        val magazineEdition = MagazineEdition(magazine, number)
        magazineEdition.add(topics)
        return magazineEdition
    }
}
