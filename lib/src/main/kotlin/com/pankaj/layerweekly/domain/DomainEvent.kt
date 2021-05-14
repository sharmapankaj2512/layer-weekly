package com.pankaj.layerweekly.domain

import java.time.ZonedDateTime

interface DomainEvent {
    fun occurredOn(): Long {
        return ZonedDateTime.now().toEpochSecond()
    }
}
