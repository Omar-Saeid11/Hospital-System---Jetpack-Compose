package com.example.hospitalsystem.presentation.mapper.loginDomainToPresentation

import com.example.hospitalsystem.domain.models.login.DomainLogin
import com.example.hospitalsystem.domain.models.login.DomainModelLogin
import com.example.hospitalsystem.presentation.models.login.PresentationLogin
import com.example.hospitalsystem.presentation.models.login.PresentationModelLogin

fun DomainLogin.toPresentationLogin(): PresentationLogin {
    return PresentationLogin(
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

fun DomainModelLogin.toPresentationModelLogin(): PresentationModelLogin {
    return PresentationModelLogin(
        data = this.data.toPresentationLogin(),
        message = this.message,
        status = this.status
    )
}
