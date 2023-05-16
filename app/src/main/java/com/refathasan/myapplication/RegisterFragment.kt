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
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
    /**
     * validateForm method to check email, password
     *
    **/
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

            userName.text.toString().isNotEmpty() && password.text.toString()
                .isNotEmpty() && confirmPassword.text.isNotEmpty() -> {
                //regex email = [a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+
                if (userName.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
                    if (password.text.toString().length >= 5) {
                        if (password.text.toString() == confirmPassword.text.toString()) {
                            //temporary Toast message would be change in final version
                            Toast.makeText(context, "Register Successful", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            confirmPassword.setError("Password mismatched", icon)
                        }
                    } else {
                        password.setError("Please Enter password at least 5 character long", icon)
                    }
                } else {
                    userName.setError("Please Enter valid email-address", icon)
                }
            }
        }
    }

}


