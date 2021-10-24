package de.hsworms.vl.android.androidminiproject_secondactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

private const val EXTRA_RESULT = "com.hs_worms.android.secondactivity.result"

class MainActivity : AppCompatActivity() {

    private lateinit var goToSecondActivityButton: Button
    private lateinit var goToSecondActivityButtonForResult: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goToSecondActivityButton = findViewById(R.id.go_to_second_activity_button)
        goToSecondActivityButtonForResult = findViewById(R.id.go_to_second_activity_button_for_result)

        goToSecondActivityButton.setOnClickListener {
            val intent = SecondActivity.newIntent(this, "Greetings from Main Activity!")
            startActivity(intent)
        }

        goToSecondActivityButtonForResult.setOnClickListener {
            val intent = SecondActivity.newIntent(this, "Greetings from Main Activity!")
            resultLauncher.launch(intent)
        }

    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data

            val text = data?.getStringExtra(EXTRA_RESULT)

            val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_LONG)
            toast.show()
        }
    }

}
