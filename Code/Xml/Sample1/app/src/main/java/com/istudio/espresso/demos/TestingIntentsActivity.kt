package com.istudio.espresso.demos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.istudio.espresso.R
import com.istudio.espresso.databinding.ActivityFormBinding
import com.istudio.espresso.databinding.ActivityResourceIdlingBinding
import com.istudio.espresso.databinding.ActivitySelectionBinding
import com.istudio.espresso.databinding.ActivityTestingIntentsBinding

class TestingIntentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestingIntentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestingIntentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dispBtnIntentId.setOnClickListener {


        }
    }

}
