package com.refathasan.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

/**
 *ProductAdapter class
 */
class ProductAdapter(private var productList: MutableList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    /**
     * implemented methods
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.product_page, parent, false)
        return ProductViewHolder(layoutView)
    }

    /**
     *implemented methods
     */
    override fun getItemCount(): Int {
        /**
         * Temporary number
         */
        return productList.size
    }

    /**
     *implemented methods
     */
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        Picasso.get()
            .load(productList[position].image)
            .into(holder.productImage)
        //Picasso.get()
          //  .load("https://firebasestorage.googleapis.com/v0/b/shopeassy-2e18b.appspot.com/o/parle_g.jpeg?alt=media&token=2248773c-db0a-4a41-a64f-158988c37494")
        //holder.productImage.setImageResource(productList[position].image)
        holder.productTitle.setText(productList[position].title)
        holder.productDetails.setText(productList[position].description)
        holder.productPrice.setText("$ "+productList[position].price)
    }

    /**
     * ViewHolder Class
     */
    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var productImage: ImageView = view.findViewById(R.id.product_image)
        var productTitle: TextView = view.findViewById(R.id.product_title)
        var productDetails: TextView = view.findViewById(R.id.product_desc)
        var productPrice: TextView = view.findViewById(R.id.product_price)
    }
}