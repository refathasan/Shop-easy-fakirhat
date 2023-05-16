package com.refathasan.myapplication

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
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private lateinit var userName: EditText
    private lateinit var password: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_login, container, false)
        userName = view.findViewById(R.id.edt_user_name)
        password = view.findViewById(R.id.edt_password)
        view.findViewById<Button>(R.id.btn_register).setOnClickListener {
            var navRegister = activity as FragmentNavigationInterface
            navRegister.navifateFragment(RegisterFragment(), false)

        }
        view.findViewById<Button>(R.id.btn_login).setOnClickListener {
            vaidateForm()
        }
        return view
    }

    private fun vaidateForm() {
        val icon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_alert)
        icon?.setBounds(0, 0, icon.intrinsicWidth, icon.intrinsicHeight)
        when {
            TextUtils.isEmpty(userName.text.toString().trim()) -> {
                userName.setError("Please Enter User Name", icon)
            }

            TextUtils.isEmpty(password.text.toString().trim()) -> {
                password.setError("Please Enter Password", icon)
            }

            userName.text.toString().isNotEmpty() && password.text.toString()
                .isNotEmpty() -> {
                if (userName.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
                    //Temporary Toast
                    Toast.makeText(context,"Login Successful",Toast.LENGTH_SHORT).show()
                } else {
                    userName.setError("Please Enter valid email-address", icon)
                }
            }
        }
    }

}