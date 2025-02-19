package com.example.appejemplo.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.appejemplo.R
import com.example.appejemplo.databinding.FragmentAnimeDetailBinding
import com.example.appejemplo.viewmodels.AnimeDetailViewModel


class AnimeDetailFragment : Fragment() {
   private lateinit var binding: FragmentAnimeDetailBinding
   private val viewModel: AnimeDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimeDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}