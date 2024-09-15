package com.example.data

import com.example.models.ApiResponse
import com.example.models.Hero
import com.example.models.Image
import com.example.util.Hero_Collection
import com.example.util.Image_Collection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.gridfs.GridFSBucket
import com.mongodb.client.gridfs.GridFSBuckets
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts.ascending

class MongoDatasourceImpl(private val db: MongoDatabase) : Datasource {
    private val heroDb = db.getCollection(Hero_Collection, Hero::class.java)
    private val imgDb = db.getCollection(Image_Collection, Image::class.java)
    override suspend fun getGridFSBucket(): GridFSBucket {
        return GridFSBuckets.create(db)
    }

    override suspend fun addImage(image: Image): Boolean {
        return imgDb.insertOne(image).wasAcknowledged()
    }

    override suspend fun getImage(name: String): Image? {
        return imgDb.withDocumentClass(Image::class.java).find(Filters.eq("_id", name)).first()
    }

    override suspend fun getAllHeroes(page: Int, pageSize: Int): ApiResponse {
        val list = heroDb.find().sort(ascending("_id"))
            .skip((page - 1) * pageSize)
            .limit(pageSize)
            .toList()
        val hasMore = heroDb.find()
            .sort(ascending("_id"))
            .skip(page * pageSize)
            .limit(1)
            .toList()
            .isNotEmpty()
        return ApiResponse(
            success = true,
            message = "ok",
            prevPage = if (page == 1) null else page - 1,
            nextPage = if (hasMore) page + 1 else null,
            heroes = list,
            lastUpdated = System.currentTimeMillis()
        )
    }

    override suspend fun addHero(hero: Hero): Boolean {
        return heroDb.insertOne(hero).wasAcknowledged()
    }

    override suspend fun searchHero(name: String, page: Int, pageSize: Int): ApiResponse {
        val list = heroDb.withDocumentClass(Hero::class.java).find(Filters.regex("name", ".*$name.*", "i"))
        val heroes = list.skip((page - 1) * pageSize)
            .limit(pageSize)
            .toList()

        val hasMore = list
            .skip(page * pageSize)
            .limit(1)
            .toList()
            .isNotEmpty()

        return ApiResponse(
            success = true,
            message = "ok",
            prevPage = if (page == 1) null else page - 1,
            nextPage = if (hasMore) page + 1 else null,
            heroes = heroes,
        )
    }
}