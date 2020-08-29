package com.nixesea.diffutillexample.model

class Mapper {
    companion object {
        fun ArrayList<Content>.toListItemsType(): ArrayList<ListItemsType.Content> {
            val result: ArrayList<ListItemsType.Content> = ArrayList()
            this.forEach {
                val mappedContent = ListItemsType.Content(
                    id = it.id,
                    name = it.name,
                    distance = it.distance,
                    address = it.address,
                    stars = it.stars,
                    suites_availability = it.suites_availability
                )
                result.add(mappedContent)
            }
            return result
        }
    }
}