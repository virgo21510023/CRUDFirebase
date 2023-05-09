package com.example.crudfirebase2

class data_mahasiswa {
    //Deklarasi Variable
    var nim: String? = null
    var nama: String? = null
    var jurusan: String? = null
    var jenisKelamin: String? = null
    var alamat: String? = null
    var key: String? = null

    //Membuat Konstuktor kosong untuk membaca data snapshot
    constructor() {}

    //Konstruktor dengan beberapa parameter, untuk mendapatkan Input Data dari User
    constructor(
        nim: String?,
        nama: String?,
        jurusan: String?,
        jenisKelamin: String?,
        alamat: String?
    ) {
        this.nim = nim
        this.nama = nama
        this.jurusan = jurusan
        this.jenisKelamin = jenisKelamin
        this.alamat = alamat
    }
}