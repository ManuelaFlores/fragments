package com.manuflowers.fragmentsandfirebase

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_simple.*
import kotlin.math.log

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SimpleFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SimpleFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SimpleFragment : Fragment() {
    // TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private  val YES = 1
    private  val NO = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater
            .inflate(R.layout.fragment_simple, container, false)

        val prueba = rootView.findViewById<RadioGroup>(R.id.radio_group)
        prueba?.let {
            it.setOnCheckedChangeListener { radioGroup, checkedID ->
                val radioButton = radioGroup.findViewById<View>(checkedID)
                val index = radioGroup.indexOfChild(radioButton)
                val textView = fragment_header
                when(index){
                    YES -> textView.text = getString(R.string.yes_message)
                    NO -> textView.text = getString(R.string.no_message)
                }

            }
        }

        val ratingBar = rootView.findViewById<RatingBar>(R.id.rating_bar)
        ratingBar.numStars = 5
        ratingBar.stepSize = 1F
        ratingBar.setOnRatingBarChangeListener { ratingBar, fl, b ->
            Toast.makeText(context,"$fl",Toast.LENGTH_SHORT).show()
            Log.e("RATING BAR","$ratingBar")
            Log.e("FL","$fl")
            Log.e("B","$b")

        }
        return rootView
    }

    fun newInstance() : SimpleFragment {
        return SimpleFragment()
    }
}



