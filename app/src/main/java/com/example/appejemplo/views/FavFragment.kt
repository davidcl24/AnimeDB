package com.example.appejemplo.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.appejemplo.App
import com.example.appejemplo.databinding.FragmentFavBinding
import com.example.appejemplo.viewmodels.FavViewModel
import com.example.appejemplo.viewmodels.FavViewModelFactory


class FavFragment : Fragment() {
    private lateinit var binding: FragmentFavBinding
    private val viewModel: FavViewModel by viewModels {
        FavViewModelFactory(App.db.animeDao())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun deleteContact(position: Int) {
        TODO("Not yet implemented")
    }

}