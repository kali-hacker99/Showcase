package com.first.showcasetrial.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.first.showcasetrial.AccountSettingsActivity
import com.first.showcasetrial.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
     var edit_account =view.findViewById<Button>(R.id.edit_account_settings_btn)

        edit_account.setOnClickListener{
            startActivity((Intent(context, AccountSettingsActivity::class.java)))
        }
    return view
    }
}

