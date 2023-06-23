package stm.virgo.journal

import android.util.Log
import retrofit2.Call
import retrofit2.Response

class Presenter(val crudView: MainActivity) {
    //Fungsi GetData
    fun getData() {
        NetworkConfig.getService().getData()
            .enqueue(object : retrofit2.Callback<ResultJournal> {
                override fun onFailure(call: Call<ResultJournal>, t: Throwable) {
                    crudView.onFailedGet(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultJournal>, response: Response<ResultJournal>) {
                    if (response.isSuccessful) {
                        val status = response.body()?.status
                        if (status == 200) {
                            val data = response.body()?.journal
                            crudView.onSuccessGet(data)
                        } else {
                            crudView.onFailedGet("Error $status")
                        }
                    }
                }
            })
    }

    //Hapus Data
    fun hapusData(id: String?) {
        NetworkConfig.getService()
            .deleteJournal(id)
            .enqueue(object : retrofit2.Callback<ResultStatus> {
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorDelete(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResultStatus>,
                    response: Response<ResultStatus>
                ) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.onSuccessDelete(response.body()?.pesan ?: "")
                    } else {
                        crudView.onErrorDelete(response.body()?.pesan ?: "")
                    }
                }
            })
    }
}