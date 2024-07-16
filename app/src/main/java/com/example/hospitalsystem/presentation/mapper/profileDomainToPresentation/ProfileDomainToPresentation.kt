package com.example.hospitalsystem.presentation.mapper.profileDomainToPresentation

import com.example.hospitalsystem.domain.models.profile.DomainModelProfile
import com.example.hospitalsystem.domain.models.profile.DomainProfile
import com.example.hospitalsystem.presentation.models.profile.PresentationModelProfile
import com.example.hospitalsystem.presentation.models.profile.PresentationProfile

fun DomainProfile.toPresentationProfile(): PresentationProfile {
    return PresentationProfile(
        accessToken,
        address,
        birthday,
        createdAt,
        email,
        firstName,
        gender,
        id,
        lastName,
        mobile,
        specialist,
        status,
        tokenType,
        type,
        verified
    )
}

fun DomainModelProfile.toPresentationModelProfile(): PresentationModelProfile {
    return PresentationModelProfile(
        data = data?.toPresentationProfile(),
        message = message,
        status = status
    )
}