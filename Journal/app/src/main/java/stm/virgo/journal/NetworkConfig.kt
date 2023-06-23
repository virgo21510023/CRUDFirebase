package stm.virgo.journal

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

object NetworkConfig {
    fun getInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return okHttpClient
    }

    //Retrofit
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2/server_api/index.php/ServerApiJournal/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() = getRetrofit().create(JournalService::class.java)
}

interface JournalService {
    //Fungsi Create Data
    @FormUrlEncoded
    @POST("addJournal")
    fun addStaff(
        @Field("title") title: String,
        @Field("content") content: String,
    ): Call<ResultStatus>

    //Fungsi Get Data
    @GET("getJournal")
    fun getData(): Call<ResultJournal>

    //Fungsi Delete Data
    @FormUrlEncoded
    @POST("deleteJournal")
    fun deleteJournal(@Field("id") id: String?): Call<ResultStatus>

    //Fungsi Update Data
    @FormUrlEncoded
    @POST("updateJournal")
    fun updateJournal(
        @Field("id") id: String,
        @Field("title") title: String,
        @Field("content") content: String
    ): Call<ResultStatus>

}