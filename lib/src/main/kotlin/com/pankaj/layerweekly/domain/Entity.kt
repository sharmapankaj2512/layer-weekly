package com.pankaj.layerweekly.domain

abstract class Entity(open val id: Id) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return this.id == (other as Entity).id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}