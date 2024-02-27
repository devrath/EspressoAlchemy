package com.istudio.espresso.demos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.istudio.espresso.R
import com.istudio.espresso.databinding.ActivityFormBinding
import com.istudio.espresso.databinding.ActivitySelectionBinding

class FormScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTextCheckId.setOnClickListener {
            binding.edtInputFieldId.setText("Updated text")
        }
    }

}