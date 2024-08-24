package com.example.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

@Serializable
data class Hero(
    @BsonId
    val id:Int,
    val name: String,
    val image:String,
    val about:String,
    val rating:Double,
    val power:Int,
    val month:String,
    val day:String,
    val family:List<String>,
    val abilities:List<String>,
    val natureTypes:List<String>
)