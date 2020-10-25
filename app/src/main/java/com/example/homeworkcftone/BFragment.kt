package com.example.homeworkcftone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation


class BFragment : Fragment() {
    private lateinit var back: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // back.findViewById<Button>(R.id.Back)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_b,container,false)
       view.findViewById<Button>(R.id.Back).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.NavigateToA)
        }
        view.findViewById<Button>(R.id.Next).setOnClickListener {
           Navigation.findNavController(view).navigate(com.example.homeworkcftone.R.id.NavigateToC)
       }
        return view
    }


}