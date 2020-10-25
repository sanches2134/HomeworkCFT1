package com.example.homeworkcftone

import android.Manifest
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.os.SystemClock
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_READ_CONTACTS = 1
    private var READ_CONTACTS_GRANTED = false

    var contactList: ListView? = null
    var contacts = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        contactList=findViewById<ListView>(R.id.ContactList)
        val hasReadContactPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)

        if (hasReadContactPermission == PackageManager.PERMISSION_GRANTED) {
            READ_CONTACTS_GRANTED = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                REQUEST_CODE_READ_CONTACTS
            )
        }
        if (READ_CONTACTS_GRANTED) {
            val job: Job = GlobalScope.launch(Dispatchers.IO) {
                loadContacts()
            }

        }

    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_READ_CONTACTS -> if (grantResults.size > 0 && grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                READ_CONTACTS_GRANTED = true
            }
        }
        if (READ_CONTACTS_GRANTED) {
        } else {
            Toast.makeText(this, "Требуется установить разрешения", Toast.LENGTH_LONG).show()
        }

    }
    private fun loadContacts()
    {
        val contentResolver = contentResolver
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val contact = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
                )
                contacts.add(contact)
            }
            cursor.close()
        }
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, contacts
        )
        contactList!!.adapter = adapter
    }


}