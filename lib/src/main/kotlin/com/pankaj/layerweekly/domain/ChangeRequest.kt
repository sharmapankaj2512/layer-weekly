package com.pankaj.layerweekly.domain

import com.pankaj.layerweekly.commands.ChangeSuggested
import java.util.*

class ChangeRequest(
    override val id: Id = Id(UUID.randomUUID()),
    val article: Article,
    val content: String
) : Aggregate(id) {
    init {
        raise(ChangeSuggested(this))
    }
}
