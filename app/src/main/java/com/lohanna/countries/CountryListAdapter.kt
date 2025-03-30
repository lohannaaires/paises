package com.lohanna.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lohanna.countries.databinding.CountryViewHolderBinding

class CountryListAdapter(private val countriesList: List<Map<String,String>>): RecyclerView.Adapter<CountryViewHolder>() {

    private lateinit var binding: CountryViewHolderBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        binding = CountryViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countriesList[position])

        holder.itemView.setOnClickListener {
            setCountryClickListener?.let {
                it(position)
            }
        }
    }

    override fun getItemCount(): Int = countriesList.size

    private var setCountryClickListener : ((Int) -> Unit)? = null

    fun onCountryClicked(listener:(Int) -> Unit) {
        setCountryClickListener = listener
    }

}