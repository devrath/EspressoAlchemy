package com.istudio.espresso.demos.helper_activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import com.istudio.espresso.R


/**
 * This a placeholder Activity for a contacts screen. This activity is never opened and does not
 * contain any real contact data for keeping this sample simple and focused.
 */
class ContactsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        setResult(RESULT_OK, createResultData("9844802176"))
        finish()
    }

    companion object {
        const val KEY_PHONE_NUMBER = "key_phone_number"
        @VisibleForTesting
        fun createResultData(phoneNumber: String?): Intent {
            val resultData = Intent()
            resultData.putExtra(KEY_PHONE_NUMBER, phoneNumber)
            return resultData
        }
    }
}