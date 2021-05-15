package com.pankaj.layerweekly.commands

import com.pankaj.layerweekly.domain.Id
import com.pankaj.layerweekly.repositories.MagazineEditionRepository
import com.pankaj.layerweekly.shared.Messages
import java.lang.IllegalArgumentException

class AddTopicsToMagazineEdition(private val editionRepository: MagazineEditionRepository) {
    fun execute(request: AddTopicsToMagazineEditionRequest) {
        editionRepository.find(request.id, request.number)?.let { edition ->
            edition.add(request.topics)
            editionRepository.update(edition)
        } ?: throw IllegalArgumentException(Messages.MAGAZINE_DOES_NOT_EXIST)
    }
}

data class AddTopicsToMagazineEditionRequest(
    val id: Id,
    val number: String,
    val topics: Set<String>)
