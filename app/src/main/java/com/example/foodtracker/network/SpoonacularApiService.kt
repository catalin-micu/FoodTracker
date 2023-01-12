package com.example.foodtracker.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

var API_KEY = "d626d5c69b9a4105bc661dd2f5ead884"
var BASE_URL = "https://api.spoonacular.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("apiKey", API_KEY)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .build()
    )
    .build()

interface SpoonacularApiService {
    @GET("recipes/findByNutrients")
    suspend fun getRecipesWithFilter(
        @Query("minCarbs") minCarbs: Int,
        @Query("maxCarbs") maxCarbs: Int,
//        @Query("minProtein") minProtein: Int,
//        @Query("maxProtein") maxProtein: Int,
//        @Query("minFat") minFat: Int,
//        @Query("maxFat") maxFat: Int,
        @Query("minCalories") minCalories: Int,
        @Query("maxCalories") maxCalories: Int,
    ): List<RecipeDataClass>
}

object SpoonacularApi {
    val retrofitService : SpoonacularApiService by lazy {
        retrofit.create(SpoonacularApiService::class.java) }
}