package com.example.paginationapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.paginationapp.databinding.RickyMortyLayoutBinding
import com.example.paginationapp.model.RickyMorty

class RickyMortyPageAdapter : PagingDataAdapter<RickyMorty, RickyMortyPageAdapter.MyViewHolder>(diffCallback) {

    inner class MyViewHolder(val binding:RickyMortyLayoutBinding):
    ViewHolder(binding.root)

    companion object{
        val diffCallback = object : DiffUtil.ItemCallback<RickyMorty>(){
            override fun areItemsTheSame(oldItem: RickyMorty, newItem: RickyMorty): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RickyMorty, newItem: RickyMorty): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.binding.apply {
            tvDescription.text = "${currentItem?.name}"
            val imageLink = currentItem?.image

            imageView.load(imageLink){
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RickyMortyLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }
}