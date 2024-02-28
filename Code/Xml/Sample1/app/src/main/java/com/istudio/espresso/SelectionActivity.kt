package com.istudio.espresso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.istudio.espresso.R
import com.istudio.espresso.databinding.ActivitySelectionBinding
import com.istudio.espresso.demos.FormScreenActivity
import com.istudio.espresso.demos.MainActivity
import com.istudio.espresso.demos.ResourceIdlingActivity
import com.istudio.espresso.utils.openActivity

class SelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.apply {
            btnMatchByTextId.setOnClickListener { openActivity(MainActivity::class.java) }
            btnBtnActionId.setOnClickListener { openActivity(FormScreenActivity::class.java) }
            btnIdlingResourceId.setOnClickListener { openActivity(ResourceIdlingActivity::class.java) }
        }
    }

}