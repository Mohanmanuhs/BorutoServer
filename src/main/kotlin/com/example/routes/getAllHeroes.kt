package com.example.routes

import com.example.data.Datasource
import com.example.models.ApiResponse
import com.example.util.GetAllHeroesRoute
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getAllHeroes() {
    val datasource:Datasource by inject()
    get(GetAllHeroesRoute){
        try {
            val page= call.request.queryParameters["page"]?.toInt() ?:1
            val pageSize= call.request.queryParameters["pageSize"]?.toInt() ?:3

            val apiResponse = datasource.getAllHeroes(page = page,pageSize)
            call.respond(
                message = apiResponse,
                status = HttpStatusCode.OK
            )
        }catch (e:NumberFormatException){
            call.respond(message = ApiResponse(success = false, message = "only numbers allowed"),
                status = HttpStatusCode.BadRequest)

        }catch (e:IllegalArgumentException){
            call.respond(message = ApiResponse(success = false, message = "Heroes not found")
            , status = HttpStatusCode.BadRequest)
        }
    }
}