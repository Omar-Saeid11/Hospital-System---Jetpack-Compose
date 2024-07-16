package com.example.hospitalsystem.data.mapper.hrDataToDomain

import com.example.hospitalsystem.data.models.hr.getUserType.ModelUserType
import com.example.hospitalsystem.data.models.hr.getUserType.UserType
import com.example.hospitalsystem.data.models.hr.register.ModelRegister
import com.example.hospitalsystem.data.models.hr.register.Register
import com.example.hospitalsystem.domain.models.hr.register.DomainModelRegister
import com.example.hospitalsystem.domain.models.hr.register.DomainRegister
import com.example.hospitalsystem.domain.models.hr.userType.DomainModelUserType
import com.example.hospitalsystem.domain.models.hr.userType.DomainUserType

fun ModelUserType.toDomainModel(): DomainModelUserType {
    return DomainModelUserType(
        data = this.data.map { it.toDomainUserType() },
        message = this.message,
        status = this.status
    )
}

fun UserType.toDomainUserType(): DomainUserType {
    return DomainUserType(
        id = this.id,
        firstName = this.firstName,
        avatar = this.avatar,
        type = this.type
    )
}

fun ModelRegister.toDomainModel(): DomainModelRegister {
    return DomainModelRegister(
        data = this.data.toDomainRegister(),
        message = this.message,
        status = this.status
    )
}

fun Register.toDomainRegister(): DomainRegister {
    return DomainRegister(
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

