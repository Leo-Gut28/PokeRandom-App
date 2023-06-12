package com.example.poke_recycle


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.random.Random

data class Item(val imageResource: String, val name: String, val weight: String)
class MainActivity : AppCompatActivity() {
    var pokeImage = ""
    var pokeName = ""
    var pokeWeight = ""
    private lateinit var pokeList: MutableList<Item>
    private lateinit var rvPoke: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPoke = findViewById(R.id.poke_list)
        pokeList = mutableListOf()
        getPokeImageURL()
    }

    private fun getPokeImageURL() {
        val client = AsyncHttpClient()
        for (i in 1..20) {
            var pokemon = Random.nextInt(151)
            client["https://pokeapi.co/api/v2/pokemon/$pokemon", object : JsonHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                    pokeImage = json.jsonObject.getJSONObject("sprites").getJSONObject("other").getJSONObject("official-artwork").getString("front_default")
                    pokeName = json.jsonObject.getString("name")
                    pokeWeight = json.jsonObject.getString("weight")
                    Log.d("Poke Success", pokeImage)
                    val onePoke = Item(pokeImage, pokeName, pokeWeight)
                    pokeList.add(onePoke)
                    val adapter = PokeAdapter(pokeList)
                    rvPoke.adapter = adapter
                    rvPoke.layoutManager = LinearLayoutManager(this@MainActivity)
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    throwable: Throwable?
                ) {
                    Log.d("Poke Error", errorResponse)
                }
            }]
        }

    }
}