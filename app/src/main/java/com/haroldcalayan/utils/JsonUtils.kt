package com.haroldcalayan.utils

import com.google.gson.Gson

object JsonUtils {

    private val gson = Gson()

    fun getGson() = gson
}