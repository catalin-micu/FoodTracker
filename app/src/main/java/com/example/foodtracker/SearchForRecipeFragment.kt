package com.example.foodtracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.foodtracker.databinding.FragmentSearchForRecipeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SearchForRecipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchForRecipeFragment : Fragment() {

    private val viewModel: SearchForRecipeViewModel by lazy {
        ViewModelProvider(this).get(SearchForRecipeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSearchForRecipeBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        setHasOptionsMenu(true)
        return binding.root
    }

}