package com.refathasan.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeFragment : Fragment() {
    var productList = ArrayList<Product>()
    var product1 = Product(R.drawable.placeholder, "Dress", "Dress for children", "30")
    var product2 = Product(androidx.core.R.drawable.ic_call_answer, "Food", "Supply for all", "15")
    var product3 = Product(R.drawable.placeholder, "Glossary", "Monthly supply", "110")
    var product4 = Product(R.drawable.placeholder, "Glass", "Glass for drinking", "20")
    var product5 = Product(R.drawable.placeholder, "Medicine", "Necessary for all", "25")
    var product6 = Product(com.google.android.material.R.drawable.ic_m3_chip_close, "Milk", "Milk for children", "8")
    var product7 = Product(R.drawable.placeholder, "Tea", "Drinks for Adults", "31")
    var product8 = Product(R.drawable.placeholder, "Oats Meal", "Food for children", "30")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview_home)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            GridLayoutManager(context,
                2,
                GridLayoutManager.VERTICAL,
                false)
        productList.add(product1)
        productList.add(product2)
        productList.add(product3)
        productList.add(product4)
        productList.add(product5)
        productList.add(product6)
        productList.add(product7)
        productList.add(product8)
        val adapter = ProductAdapter(productList)
        recyclerView.adapter = adapter
        return view
    }

}