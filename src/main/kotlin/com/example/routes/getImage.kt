package com.example.routes

import com.example.data.Datasource
import com.example.util.GetImageRoute
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.getImage() {
    val datasource: Datasource by inject()
    get(GetImageRoute) {
        val name = call.parameters["name"] ?: return@get call.respond(HttpStatusCode.BadRequest)

        val image = datasource.getImage(name)

        if (image != null) {
            val gridFsDownloadStream = datasource.getGridFSBucket().openDownloadStream(image.gridFsId)
            call.respondOutputStream(ContentType.parse(image.contentType)) {
                gridFsDownloadStream.copyTo(this)
            }
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}