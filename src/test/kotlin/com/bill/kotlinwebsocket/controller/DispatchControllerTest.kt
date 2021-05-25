package com.bill.kotlinwebsocket.controller

import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@WebMvcTest(DispatchController::class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS) -> properties 已加
class DispatchControllerTest(@Autowired val mockMvc: MockMvc) {

//    @Autowired 這樣也可
//    @Autowired lateinit var mockMvc: MockMvc

    @Test
    internal fun `check hello without Auth`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/")
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized)
                .andExpect(MockMvcResultMatchers.content().string(""))

    }

    @Test
    @WithMockUser(username = "user", authorities = arrayOf("user"))
    internal fun `check hello`() {

        mockMvc.perform(MockMvcRequestBuilders.get("/")
                .accept(MediaType.APPLICATION_JSON)
        )
//                .andDo {
//            val authentication: Authentication = Mockito.mock(Authentication::class.java)
//// Mockito.whens() for your authorization object
//// Mockito.whens() for your authorization object
//            val securityContext = Mockito.mock(SecurityContext::class.java)
//            Mockito.`when`(securityContext.authentication).thenReturn(authentication)
//            SecurityContextHolder.setContext(securityContext)
//        }
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string("Hello"))

    }
}