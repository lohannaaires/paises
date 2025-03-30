package com.lohanna.countries

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.lohanna.countries.databinding.CountryViewHolderBinding

class CountryViewHolder(private val binding: CountryViewHolderBinding, private val context: Context): RecyclerView.ViewHolder(binding.root) {

    fun bind(country: Map<String,String>) {
        val fileName = country["icon"]
        binding.apply {
            ivFlag.setImageBitmap(fileName?.let { getBitmapFromAssets("icon/$it", context) })
            tvCountry.text = country["name"]
            tvContinent.text = country["continent"]
            tvPopulation.text = country["population"]
            tvCurrency.text = country["currency"]
            tvCapital.text = country["capital"]
            tvLanguage.text = country["language"]
            tvArea.text = context.getString(R.string.area, country["area"])
        }
    }

}