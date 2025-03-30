package com.lohanna.countries

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lohanna.countries.databinding.ActivityMainBinding
import java.io.IOException
import java.io.InputStream
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var countriesList = mutableListOf<Map<String, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
    }

    private fun setup() {
        readFile()

        val layoutManager = LinearLayoutManager(this)
        val countryListAdapter = CountryListAdapter(countriesList)
        val lastItemMargin = resources.getDimensionPixelOffset(R.dimen.item_margin)

        binding.apply {
            rvCountries.layoutManager = layoutManager
            rvCountries.adapter = countryListAdapter
            rvCountries.addItemDecoration(LastItemMarginItemDecoration(lastItemMargin))
        }

        countryListAdapter.onCountryClicked {
            val country = countriesList[it]
            startActivity(Intent(this, DetailsActivity::class.java).apply {
                putExtra("country", country as Serializable)
            })
        }

    }

    private fun readFile() {
        var string = ""

        try {
            val inputStream: InputStream = assets.open("info.txt")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            string = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val allCountries = string.split("#").toList()

        allCountries.forEach {
            if (it != "") countriesList.add(convertToMap(it))
        }

        countriesList.sortBy { it["name"] }
    }

    private fun convertToMap(a: String): Map<String, String> {
        val map = a.trim().split("\n").associate {
            val (left, right) = it.split(": ", limit = 2)
            left.trim() to right.trim()
        }

        return map
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_about, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}