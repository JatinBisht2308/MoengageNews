package com.example.newsapp.repository

import android.util.Log
import com.example.newsapp.DataMapper.getNewsRecord
import com.example.newsapp.models.NewsAPIRecord
import com.example.newsapp.models.NewsAPIResponse
import com.example.newsapp.models.RequestResult
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainRepository {

    suspend fun makeGetApiRequest(): RequestResult<NewsAPIRecord> {
        return withContext(Dispatchers.IO) {
            var httpURLConnection: HttpURLConnection? = null
            var result : RequestResult<NewsAPIRecord>
            try {
                val url = URL("https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json")

                httpURLConnection = url.openConnection() as HttpURLConnection

                val code = httpURLConnection.responseCode

                if (code != 200) {
                    throw IOException("The error from the server is $code")
                }

                val bufferedReader = BufferedReader(
                    InputStreamReader(httpURLConnection.inputStream)
                )

                val jsonStringHolder: StringBuilder = StringBuilder()

                while (true) {
                    val readLine = bufferedReader.readLine() ?: break
                    jsonStringHolder.append(readLine)
                }

                val userProfileResponse =
                    Gson().fromJson(jsonStringHolder.toString(), NewsAPIResponse::class.java)
                val response = getNewsRecord(userProfileResponse)
                result = RequestResult.Success(response)

            } catch (ioexception: IOException) {
                result = RequestResult.Error(ioexception.message.toString())
                Log.e(this.javaClass.name, ioexception.message.toString())
            } finally {
                httpURLConnection?.disconnect()
            }
            result
        }
    }
}