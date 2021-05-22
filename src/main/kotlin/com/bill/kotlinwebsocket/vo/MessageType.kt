package com.bill.kotlinwebsocket.vo

enum class MessageType {

    CHAT,

    JOIN,

    LEAVE
}

data class ChatMessage(
        val type: MessageType,
        val content: String? = null,
        val sender: String
)