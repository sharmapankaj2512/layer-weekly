package com.pankaj.layerweekly.domain

class Topic(titleRaw: String) {
    private val title = titleRaw.lowercase()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        return title == (other as Topic).title
    }

    override fun hashCode(): Int {
        return title.hashCode()
    }

    fun hasTitle(topic: String): Boolean {
        return title == topic.lowercase()
    }
}
