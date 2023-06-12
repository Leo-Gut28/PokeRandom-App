package com.example.poke_recycle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.w3c.dom.Text


class PokeAdapter (private val pokeList: List<Item>) : RecyclerView.Adapter<PokeAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val pokeImage: ImageView
        val poke_name: TextView
        val poke_weight: TextView

        init {
            pokeImage = view.findViewById(R.id.poke_image)
            poke_name = view.findViewById(R.id.poke_name)
            poke_weight = view.findViewById(R.id.poke_weight)
        }

        fun bind(currentItem: Item) {
            Glide.with(itemView.context).load(currentItem.imageResource).into(pokeImage)
            poke_name.text = currentItem.name
            poke_weight.text = currentItem.weight
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.poke_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = pokeList[position]
        holder.bind(currentItem)

    }

    override fun getItemCount() = pokeList.size
}
