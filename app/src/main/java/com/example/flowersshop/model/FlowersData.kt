package com.example.flowersshop.model

import java.util.UUID

data class FlowersData(
    val id: UUID ,
    val image: String,
    val title: String,
    val score: String,
    val sells: String,
    val price: String
)
