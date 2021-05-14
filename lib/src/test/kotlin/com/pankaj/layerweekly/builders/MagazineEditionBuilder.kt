package com.pankaj.layerweekly.builders

import com.pankaj.layerweekly.domain.Magazine
import com.pankaj.layerweekly.domain.MagazineEdition

data class MagazineEditionBuilder(
    val magazine: Magazine = MagazineBuilder().build(),
    val number: String = "1"
) {

    fun build(): MagazineEdition {
        return MagazineEdition(magazine, number)
    }
}
