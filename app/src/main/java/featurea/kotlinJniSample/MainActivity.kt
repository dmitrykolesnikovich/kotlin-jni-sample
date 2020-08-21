package featurea.kotlinJniSample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import featurea.kotlinJniSample.store.Store

class MainActivity : AppCompatActivity() {

    private val keyEditText by lazy { findViewById<EditText>(R.id.editTextKey) }
    private val valueEditText by lazy { findViewById<EditText>(R.id.editTextValue) }
    private val spinner by lazy { findViewById<Spinner>(R.id.spinner) }

    private val store = Store()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner.adapter = ArrayAdapter.createFromResource(this, R.array.types_array, android.R.layout.simple_spinner_item).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }

    fun onSaveClick(view: View) {
        val keyText = keyEditText.text.toString()
        val valueText = valueEditText.text.toString()
        when (spinner.selectedItemPosition) {
            0 -> store.setString(keyText, valueText)
            1 -> store.setInteger(keyText, valueText.toInt())
            2 -> store.setFloat(keyText, valueText.toFloat())

        }
        this.keyEditText.setText("")
        this.valueEditText.setText("")
    }

    fun onGetClick(view: View) {
        val keyText = keyEditText.text.toString()
        try {
            when (spinner.selectedItemPosition) {
                0 -> valueEditText.setText(store.getString(keyText))
                1 -> valueEditText.setText(store.getInteger(keyText).toString())
                2 -> valueEditText.setText(store.getFloat(keyText).toString())
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }

    }

    fun onCheckKeyClick(view: View) {
        val keyText = keyEditText.text.toString()
        val hasEntry = store.hasEntry(keyText)
        Toast.makeText(this, "has entry: $hasEntry", Toast.LENGTH_SHORT).show()
    }

}
