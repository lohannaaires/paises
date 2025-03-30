package com.lohanna.countries

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.lohanna.countries.databinding.ActivityAboutBinding


class AboutActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.title_about)
        }

        binding.tvAbout.apply {
            movementMethod = LinkMovementMethod.getInstance()
            removeLinksUnderline()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }

}