package com.refathasan.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomeFragment : Fragment() {
    private var  recyclerView: RecyclerView? =null
    var productList = ArrayList<Product>()
    private var database: FirebaseDatabase? = null
    private var reference: DatabaseReference? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        database = FirebaseDatabase.getInstance()
        reference = database?.getReference("data")
        val firebaseListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val child = snapshot.children
                child.forEach {
                    var products = Product(it.child("image").value.toString(),
                    it.child("name").value.toString(),
                    it.child("desc").value.toString(),
                    it.child("price").value.toString())
                    productList.add(products)
                }
                val adapter = ProductAdapter(productList)
                recyclerView?.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
               // TODO("Not yet implemented")
            }

        }
        reference?.addValueEventListener(firebaseListener)

        recyclerView= view.findViewById(R.id.recyclerview_home)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager =
            GridLayoutManager(
                context,
                2,
                GridLayoutManager.VERTICAL,
                false
            )


        //val adapter = ProductAdapter(productList)
        //recyclerView.adapter = adapter
        return view
    }

}