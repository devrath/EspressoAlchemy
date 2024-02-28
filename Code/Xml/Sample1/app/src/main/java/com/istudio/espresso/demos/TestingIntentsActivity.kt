package com.istudio.espresso.demos

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.istudio.espresso.databinding.ActivityTestingIntentsBinding
import com.istudio.espresso.demos.helper_activities.ContactsActivity


class TestingIntentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestingIntentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestingIntentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * <**********************> ON-ACTIVITY Result  <**********************>
     */
    private val REQUEST_CODE_CALL_PHONE_PERMISSION = 17
    // Declare the activity result launcher
    private val pickContactLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data = result.data
            val resultCode = result.resultCode

            if (resultCode == RESULT_OK) {
                data?.extras?.let {
                    val numberStr = it.getString(ContactsActivity.KEY_PHONE_NUMBER)
                    binding.editTextCallerNumber.setText(numberStr)
                }
            }
        }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_CALL_PHONE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, make the call
                    onCall(null)
                } else {
                    // Permission denied, show a message or handle accordingly
                    Toast.makeText(this, "Phone permission denied", Toast.LENGTH_SHORT).show()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
    /**
     * <**********************> ON-ACTIVITY Result  <**********************>
     */

    /**
     * <**********************> ON-CLICK METHODS  <**********************>
     */
    // Button Action :-> OnClick of OnCall
    fun onCall(view: View?) {
        // Check if CALL-PHONE runtime permission is granted
        val hasCallPhonePermission = checkIfCallPhonePermissionIsGranted()
        if (hasCallPhonePermission) {
            // Permission is available --> Call the PhoneActivity with Intent
            startActivity(createCallIntentFromNumber())
        } else {
            // Request permission if not granted
            requestCallPhonePermission()
        }
    }

    // Button Action :-> OnClick of PickContact --> Launch the contact application
    fun onPickContact(view: View?) {
        // Start the activity for result using the activity result launcher
        val pickContactIntent = Intent(this, ContactsActivity::class.java)
        pickContactLauncher.launch(pickContactIntent)
    }

    /**
     * <**********************> ON-CLICK METHODS  <**********************>
     */

    /**
     * <**********************> HELPER METHODS  <**********************>
     */
    private fun checkIfCallPhonePermissionIsGranted() = ContextCompat.checkSelfPermission(
        this, Manifest.permission.CALL_PHONE
    ) == PackageManager.PERMISSION_GRANTED

    private fun requestCallPhonePermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),
            REQUEST_CODE_CALL_PHONE_PERMISSION
        )
    }

    private fun createCallIntentFromNumber(): Intent {
        val intentToCall = Intent(Intent.ACTION_CALL)
        val number = binding.editTextCallerNumber.text.toString()
        intentToCall.data = Uri.parse("tel:$number")
        return intentToCall
    }

    /**
     * <**********************> HELPER METHODS  <**********************>
     */

}
