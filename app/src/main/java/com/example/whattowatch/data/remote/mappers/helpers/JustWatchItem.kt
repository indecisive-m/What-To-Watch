package com.example.whattowatch.data.remote.mappers.helpers

import com.example.whattowatch.data.remote.dto.JustWatchItem
import com.example.whattowatch.domain.model.JustWatchItem as DomainJustWatchItem

fun JustWatchItem.toDomain(): DomainJustWatchItem {
    return DomainJustWatchItem(
        logoPath = logoPath.let { "https://image.tmdb.org/t/p/w500$it" },
        providerId = providerId,
        providerName = providerName,
        displayPriority = displayPriority
    )

}