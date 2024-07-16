package com.example.hospitalsystem.data.mapper.loginDataToDomain

import com.example.hospitalsystem.data.models.login.Login
import com.example.hospitalsystem.data.models.login.ModelLogin
import com.example.hospitalsystem.domain.models.login.DomainLogin
import com.example.hospitalsystem.domain.models.login.DomainModelLogin

fun Login.toDomainLogin(): DomainLogin {
    return DomainLogin(
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
        tokenType = this.tokenType,
        type = this.type,
        verified = this.verified,
        status = this.status
    )
}

fun ModelLogin.toDomainModelLogin(): DomainModelLogin {
    return DomainModelLogin(
        data = this.data.toDomainLogin(),
        message = this.message,
        status = this.status
    )
}
