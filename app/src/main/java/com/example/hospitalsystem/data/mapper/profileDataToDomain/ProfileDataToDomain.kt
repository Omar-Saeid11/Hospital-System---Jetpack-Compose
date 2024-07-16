package com.example.hospitalsystem.data.mapper.profileDataToDomain

import com.example.hospitalsystem.data.models.profile.ModelProfile
import com.example.hospitalsystem.data.models.profile.Profile
import com.example.hospitalsystem.domain.models.profile.DomainModelProfile
import com.example.hospitalsystem.domain.models.profile.DomainProfile

fun Profile.toDomainProfile(): DomainProfile {
    return DomainProfile(
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

fun ModelProfile.toDomainModelProfile(): DomainModelProfile {
    return DomainModelProfile(
        data = data?.toDomainProfile(),
        message = message,
        status = status
    )
}