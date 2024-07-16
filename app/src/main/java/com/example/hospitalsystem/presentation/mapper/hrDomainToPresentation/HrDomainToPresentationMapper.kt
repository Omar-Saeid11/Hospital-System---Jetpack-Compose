package com.example.hospitalsystem.presentation.mapper.hrDomainToPresentation

import com.example.hospitalsystem.domain.models.hr.register.DomainModelRegister
import com.example.hospitalsystem.domain.models.hr.register.DomainRegister
import com.example.hospitalsystem.domain.models.hr.userType.DomainModelUserType
import com.example.hospitalsystem.domain.models.hr.userType.DomainUserType
import com.example.hospitalsystem.presentation.models.hr.register.PresentationModelRegister
import com.example.hospitalsystem.presentation.models.hr.register.PresentationRegister
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationModelUserType
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationUserType

// Domain to Presentation Mappers

// DomainModelRegister to PresentationModelRegister
fun DomainModelRegister.toPresentationModel(): PresentationModelRegister {
    return PresentationModelRegister(
        data = this.data.toPresentationRegister(),
        message = this.message,
        status = this.status
    )
}

// DomainRegister to PresentationRegister
fun DomainRegister.toPresentationRegister(): PresentationRegister {
    return PresentationRegister(
        accessToken = this.accessToken,
        address = this.address,
        birthday = this.birthday,
        createdAt = this.createdAt,
        email = this.email,
        firstName = this.firstName,
        gender = this.gender,
        id = this.id,
        lastName = this.lastName,
        mobile = this.mobile,
        specialist = this.specialist,
        status = this.status,
        tokenType = this.tokenType,
        type = this.type,
        verified = this.verified
    )
}

// DomainModelUserType to PresentationModelUserType
fun DomainModelUserType.toPresentationModel(): PresentationModelUserType {
    return PresentationModelUserType(
        data = this.data.map { it.toPresentationUserType() },
        message = this.message,
        status = this.status
    )
}

// DomainUserType to PresentationUserType
fun DomainUserType.toPresentationUserType(): PresentationUserType {
    return PresentationUserType(
        id = this.id,
        firstName = this.firstName,
        avatar = this.avatar,
        type = this.type
    )
}
