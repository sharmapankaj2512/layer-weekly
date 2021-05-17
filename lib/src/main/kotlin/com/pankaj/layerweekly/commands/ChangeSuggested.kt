package com.pankaj.layerweekly.commands

import com.pankaj.layerweekly.domain.ChangeRequest
import com.pankaj.layerweekly.domain.DomainEvent

data class ChangeSuggested(val changeRequest: ChangeRequest): DomainEvent
