package com.example.flowersshop.model

import java.util.UUID
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlowersData(
    val id: UUID ,
    val image: String,
    val title: String,
    val score: String,
    val sells: String,
    val price: String
): Parcelable
