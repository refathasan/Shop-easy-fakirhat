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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var firebaseAuthentication: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_login, container, false)
        userName = view.findViewById(R.id.edt_user_name_log)
        password = view.findViewById(R.id.edt_password_log)
        firebaseAuthentication = Firebase.auth
        view.findViewById<Button>(R.id.btn_register_log).setOnClickListener {
            var navRegister = activity as FragmentNavigationInterface
            navRegister.navifateFragment(RegisterFragment(), false)

        }
        view.findViewById<Button>(R.id.btn_login_log).setOnClickListener {
            validateLoginForm()

        }
        return view
    }

    private fun firebaseSingIn() {
        firebaseAuthentication.signInWithEmailAndPassword(
            userName.text.toString(),
            password.text.toString()
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val homeFragment = activity as FragmentNavigationInterface
                homeFragment.navifateFragment(HomeFragment(), true)
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(context, task.exception?.message.toString(), Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }
    private fun validateLoginForm() {
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
                    firebaseSingIn()
                    //Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                } else {
                    userName.setError("Please Enter valid email-address", icon)
                }
            }
        }
    }


}