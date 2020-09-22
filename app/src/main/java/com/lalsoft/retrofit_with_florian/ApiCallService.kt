package com.lalsoft.retrofit_with_florian

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

object ApiCallService {
//    private val gson: Gson = GsonBuilder()
//        .serializeNulls()
//        .create()

    private val httpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    /** Add header in OkHttpClient,if you want header in all*/
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(Interceptor {
            val originalRequest = it.request()
            val newRequest = originalRequest.newBuilder()
                .header("Interceptor_header", "XyZ")
                .build()
            return@Interceptor it.proceed(newRequest)
        })
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ApiCall::class.java)

    private val map = getHashMap()
    private val fields = getHashMapField()
    private val headerMap = getHeaderMap()
    private val post = Post(24, "My Post", "New Post")
    private val postPatch = PostPatch(userId = 24, text = "New Post")


    //fun callPost() = api.getPosts(4,"id","desc")
    //fun callPost() = api.getPosts(arrayOf(1,5,6),"id","desc")
    fun callPost() = api.getPosts(map)

    //fun callComment() = api.getComments(3)
    fun callComment() = api.getComments("posts/8/comments")

    //fun createPost()= api.createPost(post)
    //fun createPost() = api.createPost(26, "My Post", "New Poster")
    fun createPost() = api.createPost(fields)

    fun updatePost() = api.putPost("ABC", 1, post)
    fun patchPost() = api.patchPost(headerMap, 1, postPatch)

    fun deletePost() = api.deletePost(5)


}

fun getHashMap(): HashMap<String, String> {
    val map = HashMap<String, String>()
    map["userId"] = "10"
    map["_sort"] = "id"
    map["_order"] = "desc"
    return map
}

fun getHeaderMap(): HashMap<String, String> {
    val map = HashMap<String, String>()
    map["Map-Header1"] = "10"
    map["Map-Header1t"] = "12"

    return map
}

fun getHashMapField(): HashMap<String, String> {
    val map = HashMap<String, String>()
    map["userId"] = "24"
    map["title"] = "New Title"
    map["body"] = "My Description"
    return map
}