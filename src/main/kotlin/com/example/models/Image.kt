package com.example.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Image(
    @BsonId
    val filename: String,
    val id: Int,
    val contentType: String,
    val gridFsId: ObjectId
)