package com.zcotech.apigatewayspring

import com.zcotech.apigatewayspring.clients.AddressClient
import com.zcotech.apigatewayspring.clients.PersonClient
import com.zcotech.apigatewayspring.model.SkillsDTO
import org.mockito.Mockito.mock
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Profile("test")
class TestMocks {
    @Bean @Primary fun skillsClient() = mock<SkillsDTO>()
    @Bean @Primary fun addressClient() = mock<AddressClient>()
    @Bean @Primary fun personClient() = mock<PersonClient>()
}