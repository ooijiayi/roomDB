package com.example.roomdb.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ProductDAO {

    @Insert
    fun insert(p : Product)
    @Query("select * from product_table")
    fun getAll():List<Product>

    @Query("select*from product_table where name=:name")
    fun searchProduct(name:String):List<Product>

}