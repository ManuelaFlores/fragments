package com.manuflowers.fragmentsandfirebase

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val STATE_FRAGMENT = "state_of_fragment"

    var isFragmentDisplayed = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT)
        }

        if (isFragmentDisplayed) {
            m_button.text = getString(R.string.close)
        }

        m_button.setOnClickListener {
            if (!isFragmentDisplayed) displayFragment() else closeFragment()
        }

    }

    fun displayFragment(){
        val button = findViewById<Button>(R.id.m_button)
        val simpleFragment = SimpleFragment().newInstance()

        // Get the fragmentManager and start the transaction:
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager
            .beginTransaction()

        //Add the simpleFragment
        fragmentTransaction
            .add(R.id.fragment_container,simpleFragment)
            .addToBackStack(null)
            .commit()

        //Update button text
            button.text = getString(R.string.close)
            isFragmentDisplayed = true
    }

    fun closeFragment() {
        val button = findViewById<Button>(R.id.m_button)
        //Get the fragment manager:
        val fragmentManager = supportFragmentManager

        //check if the fragments is already showing:
        val simpleFragment = fragmentManager
            .findFragmentById(R.id.fragment_container) as SimpleFragment

        if(simpleFragment != null) {
            //Create and commit the transaction to remove the fragment:
            val fragmentTransaction = fragmentManager
                .beginTransaction()

            fragmentTransaction.remove(simpleFragment).commit()
        }

        //update button text :
        button.text = getString(R.string.open)
        isFragmentDisplayed = false

    }

    fun onSavedInstance(savedInstanceState: Bundle){
        //save the fragment state : (true= open; false= close)
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed)
        super.onSaveInstanceState(savedInstanceState)
    }
}
