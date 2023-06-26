package com.example.crudapi2

import retrofit2.Call

import retrofit2.Response

class Presenter2(val crudView: UpdateAddActivity) {
    //Add data
    fun addData(name: String, jenisKelamin: String,programStudi: String, hp: String, alamat: String) {
        NetworkConfig.getService()
            .addStaff(name, jenisKelamin, programStudi, hp, alamat)
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
    fun updateData(id: String, name: String, jenisKelamin: String,programStudi: String, hp: String, alamat: String) {
        NetworkConfig.getService()
            .updateStaff(id, name, jenisKelamin, programStudi, hp, alamat)
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