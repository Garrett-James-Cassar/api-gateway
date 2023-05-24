package com.zcotech.apigatewayspring.resource

import com.zcotech.apigatewayspring.clients.*
import com.zcotech.apigatewayspring.model.AddressDTO
import com.zcotech.apigatewayspring.model.PersonDTO
import com.zcotech.apigatewayspring.model.SkillsDTO
import com.zcotech.apigatewayspring.model.SuperDTO
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping

@Controller
class GreetingResource(
    private val skillsClient: SkillsClient,
    private val addressClient: AddressClient,
    private val personClient: PersonClient
)  {

    @PostMapping("/add")
    fun handleSuperDTO(request: RequestEntity<SuperDTO>): ResponseEntity<SuperDTO> {
        val dto = request.body!!

        // Map the SuperDTO to the smaller DTOs based on parameter names
        val skillsDTO = SkillsDTO(dto.skillLevel, dto.skillName, dto.skillType)
        val addressDTO = AddressDTO(dto.street, dto.city, dto.postalCode)
        val personDTO = PersonDTO(dto.name, dto.age, dto.email)

        println(skillsDTO)
        println(addressDTO)
        println(personDTO)

        // Fan out the requests to each related service
        skillsClient.createSkill(skillsDTO)
        addressClient.createAddress(addressDTO)
        personClient.createPerson(personDTO)

        return ResponseEntity.ok(dto)
    }
}