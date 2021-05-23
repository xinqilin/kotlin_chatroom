package com.bill.kotlinwebsocket.config

import com.bill.kotlinwebsocket.vo.ChatMessage
import com.bill.kotlinwebsocket.vo.MessageType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionConnectEvent
import org.springframework.web.socket.messaging.SessionDisconnectEvent

@Component
class WebSocketEventListener(@Autowired val simpleMessageSendingOperations: SimpMessageSendingOperations) {

    val logger: Logger = LoggerFactory.getLogger(WebSocketEventListener::class.java)


    @EventListener
    fun handleWebSocketConnectListener(sessionConnectEvent: SessionConnectEvent) {
        logger.info("receive new connection!!!")
    }

    @EventListener
    fun handleWebSocketDisconnectListener(sessionConnectEvent: SessionConnectEvent) {
        val stompHeaderAccessor: StompHeaderAccessor = StompHeaderAccessor.wrap(sessionConnectEvent.message)
        val username: String? = stompHeaderAccessor.sessionAttributes?.get("username") as String?
        if (username != null) {
            logger.info("$username leave the chatroom");
            val chatMessage = ChatMessage(
                    type = MessageType.LEAVE,
                    sender = username
            )
            simpleMessageSendingOperations.convertAndSend("/topic/public", chatMessage);
        }
    }
}