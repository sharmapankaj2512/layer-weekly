package com.pankaj.layerweekly.repositories

import com.pankaj.layerweekly.domain.Id
import com.pankaj.layerweekly.domain.MagazineEdition

interface MagazineEditionRepository {
    fun update(magazineEdition: MagazineEdition)
    fun find(id: Id): MagazineEdition?
    fun find(id: Id, number: String): MagazineEdition?
}
