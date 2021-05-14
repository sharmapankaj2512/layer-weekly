package com.pankaj.layerweekly.repositories

import com.pankaj.layerweekly.domain.Id
import com.pankaj.layerweekly.domain.MagazineEdition

interface MagazineEditionRepository {
    fun find(id: Id, number: String): MagazineEdition?
    fun update(magazineEdition: MagazineEdition)
}
