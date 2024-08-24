package com.example.data

import com.example.models.ApiResponse
import com.example.models.Hero
import com.example.models.Image
import com.mongodb.client.gridfs.GridFSBucket

interface Datasource {

    suspend fun getGridFSBucket(): GridFSBucket
    suspend fun addImage(image: Image):Boolean
    suspend fun getImage(name:String):Image?
    suspend fun getAllHeroes(page:Int,pageSize:Int):ApiResponse
    suspend fun addHero(hero: Hero):Boolean
    suspend fun searchHero(name: String,page:Int,pageSize:Int):ApiResponse
}