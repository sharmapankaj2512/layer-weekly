package com.pankaj.layerweekly.builders

import com.pankaj.layerweekly.domain.Id
import com.pankaj.layerweekly.domain.Magazine
import java.util.*

data class MagazineBuilder(
    val id: Id = Id(UUID.randomUUID()),
    val name: String = "Layer Weekly"
) {
    fun build(): Magazine {
        return Magazine(id, name)
    }
}
