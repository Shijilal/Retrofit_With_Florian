@file:Suppress("unused")

package com.lalsoft.retrofit_with_florian

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "AppDebug"

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPosts()
        //getComments()
        //createPost()
        //updatePost()
        //patchPost()
        //deletePost()


    }

    private fun getPosts() {
        ApiCallService.callPost()
            .enqueue(object : Callback<List<Post>> {
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    if (!response.isSuccessful) {
                        textView_Result.text = "Code : ${response.code()}"
                    } else {
                        val posts = response.body()
                        val sb = StringBuilder()
                        for (post in posts!!) {
                            sb.append("ID : ${post.id}\n")
                            sb.append("User ID : ${post.userId}\n")
                            sb.append("Title : ${post.title}\n")
                            sb.append("Text : ${post.text}\n\n")
                        }
                        textView_Result.text = sb.toString()
                        Log.d(TAG, "onResponse: $sb")
                    }
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    textView_Result.text = t.message
                }
            })
    }

    private fun getComments() {
        ApiCallService.callComment()
            .enqueue(object : Callback<List<Comment>> {
                override fun onResponse(
                    call: Call<List<Comment>>,
                    response: Response<List<Comment>>
                ) {
                    if (!response.isSuccessful) {
                        textView_Result.text = "Code : ${response.code()}"
                        return
                    } else {
                        val posts = response.body()
                        val sb = StringBuilder()
                        for (post in posts!!) {
                            sb.append("ID : ${post.id}\n")
                            sb.append("Post ID : ${post.postId}\n")
                            sb.append("Email : ${post.email}\n")
                            sb.append("Text : ${post.text}\n\n")
                        }
                        textView_Result.text = sb.toString()
                        Log.d(TAG, "onResponse: $sb")
                    }
                }

                override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                    textView_Result.text = t.message
                }
            })
    }

    private fun createPost() {
        ApiCallService.createPost()
            .enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (!response.isSuccessful) {
                        textView_Result.text = "Code : ${response.code()}"
                        return
                    } else {
                        val postResponse = response.body()!!
                        val sb = StringBuilder()
                        sb.append("Code : ${response.code()}\n")
                        sb.append("ID : ${postResponse.id}\n")
                        sb.append("User ID : ${postResponse.userId}\n")
                        sb.append("Title : ${postResponse.title}\n")
                        sb.append("Text : ${postResponse.text}\n\n")

                        textView_Result.text = sb.toString()
                        Log.d(TAG, "onResponse: $sb")
                    }
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    textView_Result.text = t.message
                }
            })
    }

    private fun updatePost() {
        ApiCallService.updatePost()
            .enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (!response.isSuccessful) {
                        textView_Result.text = "Code : ${response.code()}"
                        return
                    } else {
                        val postResponse = response.body()!!
                        val sb = StringBuilder()
                        sb.append("Code : ${response.code()}\n")
                        sb.append("ID : ${postResponse.id}\n")
                        sb.append("User ID : ${postResponse.userId}\n")
                        sb.append("Title : ${postResponse.title}\n")
                        sb.append("Text : ${postResponse.text}\n\n")

                        textView_Result.text = sb.toString()
                        Log.d(TAG, "onResponse: $sb")
                    }
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    textView_Result.text = t.message
                }
            })
    }

    private fun patchPost() {
        ApiCallService.patchPost()
            .enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (!response.isSuccessful) {
                        textView_Result.text = "Code : ${response.code()}"
                        return
                    } else {
                        val postResponse = response.body()!!
                        val sb = StringBuilder()
                        sb.append("Code : ${response.code()}\n")
                        sb.append("ID : ${postResponse.id}\n")
                        sb.append("User ID : ${postResponse.userId}\n")
                        sb.append("Title : ${postResponse.title}\n")
                        sb.append("Text : ${postResponse.text}\n\n")

                        textView_Result.text = sb.toString()
                        Log.d(TAG, "onResponse: $sb")
                    }
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    textView_Result.text = t.message
                }
            })
    }

    private fun deletePost() {
        ApiCallService.deletePost()
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    textView_Result.text="Code ${response.code()}"
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    textView_Result.text=t.message
                }
            })
    }
}