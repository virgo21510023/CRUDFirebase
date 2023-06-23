package stm.virgo.journal

import android.util.Log
import retrofit2.Call
import retrofit2.Response

class Presenter2(val crudView: UpdateAddActivity) {
    //Add data
    fun addData(title: String, content: String) {
        NetworkConfig.getService()
            .addStaff(title, content)
            .enqueue(object : retrofit2.Callback<ResultStatus> {
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.errorAdd(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResultStatus>,
                    response: Response<ResultStatus>
                ) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.successAdd(response.body()?.pesan ?: "")
                    } else {
                        crudView.errorAdd(response.body()?.pesan ?: "")
                    }
                }
            })
    }

    //Update Data
    fun updateData(id: String, title: String, content: String) {
        NetworkConfig.getService()
            .updateJournal(id, title, content)
            .enqueue(object : retrofit2.Callback<ResultStatus> {
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorUpdate(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResultStatus>,
                    response: Response<ResultStatus>
                ) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.onSuccessUpdate(response.body()?.pesan ?: "")
                    } else {
                        crudView.onErrorUpdate(response.body()?.pesan ?: "")
                    }
                }
            })
    }
}