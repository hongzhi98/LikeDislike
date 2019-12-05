package com.example.likedislike

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import java.util.prefs.AbstractPreferences

class MainActivity : AppCompatActivity() {
    //Module-level variable
    //var countLike:Int = 0
    lateinit var counterViewModel: CounterViewModel
    lateinit var sharePreferences: SharedPreferences
    //var countDislike:Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "onCreate")

        //Initialise the Counter ViewModel
        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)

        //initialise the Shared Preferences
        //sharePreferences = getSharedPreferences("ntk", Context.MODE_PRIVATE)
        sharePreferences = getPreferences(Context.MODE_PRIVATE)

        imageViewLike.setOnClickListener {
            counterViewModel.countLike++
            textViewLike.text = counterViewModel.countLike.toString()
        }

        imageViewDislike.setOnClickListener{
            counterViewModel.countDislike++
            textViewDislike.text = counterViewModel.countDislike.toString()
        }
    }

    override fun onStart() {
        //d = debug
        Log.d("MainActivity", "onStart")

        super.onStart()
    }

    override fun onResume() {
        //d = debug
        Log.d("MainActivity", "onResume")

        //Retrieve Shared Preference value(s)
        counterViewModel.countLike = sharePreferences.getInt(
            getString(R.string.like), 0
        )
        counterViewModel.countDislike = sharePreferences.getInt(
            getString(R.string.dislike), 0
        )

        textViewLike.text = counterViewModel.countLike.toString()
        textViewDislike.text = counterViewModel.countDislike.toString()

        super.onResume()
    }

    override fun onPause() {
        //d = debug
        Log.d("MainActivity", "onPause")

        with(sharePreferences.edit()){
            putInt(getString(R.string.like), counterViewModel.countLike)
            putInt(getString(R.string.dislike), counterViewModel.countDislike)
            commit()
        }

        super.onPause()
    }

    override fun onStop() {
        //d = debug
        Log.d("MainActivity", "onStop")

        super.onStop()
    }

    override fun onDestroy() {
        //d = debug
        Log.d("MainActivity", "onDestroy")

        super.onDestroy()
    }
}
