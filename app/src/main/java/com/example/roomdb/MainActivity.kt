package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.roomdb.data.Product
import com.example.roomdb.data.ProductDAO
import com.example.roomdb.data.ProductDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var dao: ProductDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dao = ProductDB.getInstance(application).productDao

        val btnInsert: Button = findViewById(R.id.btnInsert)
        btnInsert.setOnClickListener() {
            val name: String = findViewById<TextView>(R.id.tfName).text.toString()
            val price: Double = findViewById<TextView>(R.id.tfPrice).text.toString().toDouble()
            val p = Product(0, name, price)
            CoroutineScope(IO).launch {
                dao.insert(p)
            }
        }


        val btnGet: Button = findViewById(R.id.btnGet)
        btnGet.setOnClickListener() {
            CoroutineScope(IO).launch {
                var str = ""
                val Plist: List<Product> = dao.searchProduct("hh")
                for (p: Product in Plist) {
                    str += p.name +"   "+p.price+"\n"

                }
                CoroutineScope(Main).launch {
                    val tv: TextView = findViewById(R.id.tvResult)
                    tv.text = str
                }

            }

        }

    }
}