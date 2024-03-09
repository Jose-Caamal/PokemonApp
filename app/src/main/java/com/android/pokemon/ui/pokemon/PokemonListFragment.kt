package com.android.pokemon.ui.pokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.pokemon.R
import com.android.pokemon.retrofit.models.PokemonModel

/**
 * A fragment representing a list of Items.
 */
class PokemonListFragment : Fragment() {
    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var pokemonAdapter: MyPokemonRecyclerViewAdapter
    private var listPokemon: List<PokemonModel> = ArrayList()
    private var columnCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_list_list, container, false)
        //obtenemos el viewModel
        pokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        pokemonAdapter = MyPokemonRecyclerViewAdapter()
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = pokemonAdapter
            }
        }
        //Observer
        pokemonViewModel.getListPokemon().observe(viewLifecycleOwner, Observer{
            listPokemon = it
            pokemonAdapter.setData(listPokemon)
        })
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            PokemonListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}