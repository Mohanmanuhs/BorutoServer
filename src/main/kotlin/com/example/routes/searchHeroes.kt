package com.example.routes

import com.example.data.Datasource
import com.example.util.SearchHeroesRoute
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.searchHeroes() {
    val datasource: Datasource by inject()

    get(SearchHeroesRoute) {
        val name = call.request.queryParameters["name"]?:""
        val page = call.request.queryParameters["page"]?.toInt() ?:1
        val pageSize = call.request.queryParameters["pageSize"]?.toInt()?:3
        val apiResponse = datasource.searchHero(name = name,page,pageSize)
        call.respond(
            message = apiResponse,
            status = HttpStatusCode.OK
        )
    }
}