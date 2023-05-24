package com.zcotech.apigatewayspring.model

data class SkillsDTO(val skillLevel: Int,val skillName: String,val skillType: String)
data class AddressDTO(val street: String,val city: String,val postalCode: String)
data class PersonDTO(val name: String,val age: Int,val email: String)