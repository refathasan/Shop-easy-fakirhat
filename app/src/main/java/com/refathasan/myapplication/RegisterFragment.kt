package com.refathasan.myapplication

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.content.res.AppCompatResources


class RegisterFragment : Fragment() {
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_register, container, false)
        userName = view.findViewById(R.id.edt_user_name)
        password = view.findViewById(R.id.edt_password)
        confirmPassword = view.findViewById(R.id.edt_conf_password)
        view.findViewById<Button>(R.id.btn_login).setOnClickListener {
            var navLogin = activity as FragmentNavigationInterface
            navLogin.navifateFragment(LoginFragment(), false)
        }
        view.findViewById<Button>(R.id.btn_register).setOnClickListener {
            validateForm()
        }
        return view
    }

    private fun validateForm() {
        val icon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_alert)
        icon?.setBounds(0, 0, icon.intrinsicWidth, icon.intrinsicHeight)
        when {
            TextUtils.isEmpty(userName.text.toString().trim()) -> {
                userName.setError("Please Enter User Name", icon)
            }

            TextUtils.isEmpty(password.text.toString().trim()) -> {
                password.setError("Please Enter Password", icon)
            }

            TextUtils.isEmpty(confirmPassword.text.toString().trim()) -> {
                confirmPassword.setError("Please Enter Password Confirmation", icon)
            }
        }
    }

}


