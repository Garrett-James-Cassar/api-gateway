package com.zcotech.apigatewayspring

import com.nhaarman.mockitokotlin2.verify
import com.zcotech.apigatewayspring.clients.AddressClient
import com.zcotech.apigatewayspring.clients.PersonClient
import com.zcotech.apigatewayspring.clients.SkillsClient
import com.zcotech.apigatewayspring.model.AddressDTO
import com.zcotech.apigatewayspring.model.PersonDTO
import com.zcotech.apigatewayspring.model.SkillsDTO
import com.zcotech.apigatewayspring.model.SuperDTO
import com.zcotech.apigatewayspring.resource.GreetingResource
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.capture
import com.nhaarman.mockitokotlin2.mock
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class GreetingResourceTest {

    private val skillsClient: SkillsClient = mock()
    private val addressClient: AddressClient = mock()
    private val personClient: PersonClient = mock()

    private val greetingResource = GreetingResource(skillsClient, addressClient, personClient)

    private val skillsDtoCaptor = argumentCaptor<SkillsDTO>()
    private val addressDtoCaptor = argumentCaptor<AddressDTO>()
    private val personDtoCaptor = argumentCaptor<PersonDTO>()

    @Test
    fun `test handleSuperDTO`() {
        // given
        val superDTO = SuperDtoBuilder().build()
        val expectedResponse = ResponseEntity(superDTO, HttpStatus.OK)

        // when
        val actualResponse = greetingResource.handleSuperDTO(superDTO)

        // then
        assertEquals(expectedResponse, actualResponse)

        skillsDtoCaptor.apply { verify(skillsClient).createSkill(capture())}
        addressDtoCaptor.apply { verify(addressClient).createAddress(capture()) }
        personDtoCaptor.apply { verify(personClient).createPerson(capture()) }

        val capturedSkillsDto = skillsDtoCaptor.firstValue
        val capturedAddressDto = addressDtoCaptor.firstValue
        val capturedPersonDto = personDtoCaptor.firstValue

        assertEquals(superDTO.skillLevel, capturedSkillsDto.skillLevel)
        assertEquals(superDTO.skillName, capturedSkillsDto.skillName)
        assertEquals(superDTO.skillType, capturedSkillsDto.skillType)

        assertEquals(superDTO.street, capturedAddressDto.street)
        assertEquals(superDTO.city, capturedAddressDto.city)
        assertEquals(superDTO.postalCode, capturedAddressDto.postalCode)

        assertEquals(superDTO.name, capturedPersonDto.name)
        assertEquals(superDTO.age, capturedPersonDto.age)
        assertEquals(superDTO.email, capturedPersonDto.email)
    }

    class SuperDtoBuilder(
        private val skillLevel: Int = 1,
        private val skillName: String = "Java",
        private val skillType: String = "Programming",
        private val street: String = "123 Anywhere St",
        private val city: String = "Springfield",
        private val postalCode: String = "12345",
        private val name: String = "John Doe",
        private val age: Int = 30,
        private val email: String = "john.doe@example.com"
    ) {
        fun build(): SuperDTO {
            return SuperDTO(
                skillLevel = skillLevel,
                skillName = skillName,
                skillType = skillType,
                street = street,
                city = city,
                postalCode = postalCode,
                name = name,
                age = age,
                email = email
            )
        }
    }
}
