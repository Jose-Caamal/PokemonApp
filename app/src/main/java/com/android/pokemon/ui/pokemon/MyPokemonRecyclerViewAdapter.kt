package com.android.pokemon.ui.pokemon

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import coil.transform.CircleCropTransformation
import com.android.pokemon.R
import com.android.pokemon.common.constantes


import com.android.pokemon.databinding.FragmentPokemonListBinding
import com.android.pokemon.retrofit.models.PokemonModel


class MyPokemonRecyclerViewAdapter() : RecyclerView.Adapter<MyPokemonRecyclerViewAdapter.ViewHolder>() {

    private var pokemon: List<PokemonModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentPokemonListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = pokemon[position]
        holder.tvTitle.text = item.name
        val arrayUrl = item.url.split("/")
        item.id = arrayUrl[arrayUrl.size - 2].toInt()
        holder.imagePokemon.load(constantes.URL_PARAM_IMAGE + item.id +".png"){
            crossfade(true)
            placeholder(R.drawable.ic_pokemon)
            transformations(CircleCropTransformation())
        }
        
    }

    override fun getItemCount(): Int = pokemon.size
    fun setData(listPokemon: List<PokemonModel>?) {
        pokemon = listPokemon!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: FragmentPokemonListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imagePokemon: ImageView = binding.imageView
        val tvTitle: TextView = binding.textViewTitle

    }

}