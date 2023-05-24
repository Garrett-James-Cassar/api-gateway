package com.zcotech.apigatewayspring.clients

import com.zcotech.apigatewayspring.model.AddressDTO
import com.zcotech.apigatewayspring.model.PersonDTO
import com.zcotech.apigatewayspring.model.SkillsDTO
import org.springframework.stereotype.Service

@Service
class SkillsClient {
    fun createSkill(dto: SkillsDTO) = println(dto)
}

@Service
class AddressClient {
    fun createAddress(dto: AddressDTO) = println(dto)
}

@Service
class PersonClient {
    fun createPerson(dto: PersonDTO) = println(dto)
}

