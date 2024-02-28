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

class ResourceIdlingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResourceIdlingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResourceIdlingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTextCheckId.setOnClickListener {
            val manager = NetworkManager()
            // Request started
            binding.txtInputFieldId.text = "Started"
            manager.performNetworkRequest {
                // Request complete
                binding.txtInputFieldId.text = "Complete"
            }

        }
    }

}

class NetworkManager {

    companion object {
        const val NETWORK_DELAY : Long = 3000
    }

    fun performNetworkRequest(callback :()-> Unit){

        // Simulating a network request that takes some time
        Handler(Looper.getMainLooper()).postDelayed({
            // Invoking the callback
            callback.invoke()
        }, NETWORK_DELAY)

    }

}