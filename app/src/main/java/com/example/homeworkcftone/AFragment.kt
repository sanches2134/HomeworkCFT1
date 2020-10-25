package com.example.homeworkcftone

import android.Manifest
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlin.system.exitProcess


class AFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_a, container, false)
        view.findViewById<Button>(R.id.Next).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.NavigateToB)
        }
        view.findViewById<Button>(R.id.Exit).setOnClickListener {
            exitProcess(-1)
        }
        return view
    }

}