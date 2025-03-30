package com.lohanna.countries

import android.content.DialogInterface
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import com.lohanna.countries.databinding.ActivityDetailsBinding
import com.lohanna.countries.databinding.CountryInfoDialogBinding


class DetailsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    private lateinit var fileName: String
    private lateinit var name: String
    private lateinit var description: String
    private lateinit var continent: String
    private lateinit var capital: String
    private lateinit var language: String
    private lateinit var population: String
    private lateinit var area: String
    private lateinit var currency: String
    private lateinit var imageName: String
    private lateinit var imageAuthor: String
    private lateinit var url: String

    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }

        val bundle: Bundle? = intent.extras

        bundle?.let {
            bundle.apply {
                val map = getSerializable("country") as Map<*, *>
                fileName = map["image"] as String
                name = map["name"] as String
                description = map["description"] as String
                continent = map["continent"] as String
                capital = map["capital"] as String
                language = map["language"] as String
                population = map["population"] as String
                area = map["area"] as String
                currency = map["currency"] as String
                imageName = map["image-name"] as String
                imageAuthor = map["image-author"] as String
                url = map["url"] as String

                binding.apply {
                    toolbarLayout.title = name
                    toolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
                    toolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
                    ivPicture.setImageBitmap(getBitmapFromAssets("img/$fileName", context))
                    details.apply {
                        tvDescription.text = description
                        tvContinent.text = continent
                        tvCapital.text = capital
                        tvLanguage.text = language
                        tvPopulation.text = population
                        tvArea.text = getString(R.string.area, area)
                        tvCurrency.text = currency
                    }
                }
            }
        }
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)

        val dialogBinding = CountryInfoDialogBinding.inflate(layoutInflater)
        builder.setView(dialogBinding.root)

        val author = SpannableStringBuilder()
            .bold { append("Autor: ") }
            .append(imageAuthor)

        val imageUrl = SpannableString(getString(R.string.url))
        imageUrl.setSpan(URLSpan(url), 0, imageUrl.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        dialogBinding.apply {
            ivPlace.setImageBitmap(getBitmapFromAssets("img/$fileName", context))
            tvTitle.text = imageName

            tvAuthor.text = author

            tvLink.text = imageUrl
            tvLink.movementMethod = LinkMovementMethod.getInstance()
            tvLink.removeLinksUnderline()
        }

        builder.setPositiveButton("OK") { _, _ -> }

        val dialog = builder.create()
        dialog.show()

        dialog.getButton(DialogInterface.BUTTON_POSITIVE)
            .setTextColor(ContextCompat.getColor(context, R.color.yellow_primary))

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_about, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about -> {
                showAlertDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}