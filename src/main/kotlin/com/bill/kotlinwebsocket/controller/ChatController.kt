package com.bill.kotlinwebsocket.controller

import com.bill.kotlinwebsocket.vo.ChatMessage
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.stereotype.Controller

@Controller
class ChatController {

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    fun sendMessage(@Payload chatMessage: ChatMessage): ChatMessage = chatMessage

    @MessageMapping("/addUser")
    @SendTo("/topic/public")
    fun addUser(@Payload chatMessage: ChatMessage, simpMessageHeaderAccessor: SimpMessageHeaderAccessor): ChatMessage {
        simpMessageHeaderAccessor.sessionAttributes?.put("username", chatMessage.sender)
        return chatMessage
    }
}