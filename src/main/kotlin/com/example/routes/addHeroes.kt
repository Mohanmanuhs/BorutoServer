package com.example.routes

import com.example.data.Datasource
import com.example.models.Hero
import com.example.models.Image
import com.example.util.AddHeroesRoute
import com.example.util.UploadImageRoute
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.io.InputStream


private val auth = System.getenv("Auth")
fun Route.addHeroes() {
    val datasource: Datasource by inject()
    authenticate(auth) {
        post(AddHeroesRoute) {
            val hero = call.receive<Hero>()
            val success = datasource.addHero(hero)
            if(success){
                call.respondText("added successfully")
            }else{
                call.respondText("Failed to add")
            }
        }
        post(UploadImageRoute) {
            val multipart = call.receiveMultipart()
            var filename: String? = null
            var contentType: String? = null
            var fileStream: InputStream? = null
            var id:Int?=null

            multipart.forEachPart { part ->
                when (part) {
                    is PartData.FileItem -> {
                        filename = part.originalFileName as String
                        contentType = part.contentType?.toString()
                        fileStream = part.streamProvider()
                    }
                    is PartData.FormItem ->{
                        if(part.name == "id") {
                            id = part.value.toInt()
                        }
                    }
                    else -> part.dispose()
                }
            }
            if (filename != null && contentType != null && fileStream != null) {
                val gridFsUploadStream = datasource.getGridFSBucket().openUploadStream(filename!!)
                fileStream!!.copyTo(gridFsUploadStream)
                gridFsUploadStream.close()

                val image = id?.let { it1 ->
                    Image(
                        id = it1,
                        filename = filename!!,
                        contentType = contentType!!,
                        gridFsId = gridFsUploadStream.objectId
                    )
                }
                if (image != null) {
                    datasource.addImage(image)
                }
                call.respond(mapOf("message" to "File uploaded successfully"))
            } else {
                call.respond(mapOf("error" to "Invalid file upload"))
            }
        }
    }
}