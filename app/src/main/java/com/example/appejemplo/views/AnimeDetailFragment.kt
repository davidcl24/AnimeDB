package com.example.appejemplo.views

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.appejemplo.R
import com.example.appejemplo.databinding.FragmentAnimeDetailBinding
import com.example.appejemplo.viewmodels.AnimeDetailViewModel


class AnimeDetailFragment : Fragment() {
    private lateinit var binding: FragmentAnimeDetailBinding
    private val viewModel: AnimeDetailViewModel by viewModels()
    private val args: AnimeDetailFragmentArgs by navArgs()

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
        val identif = args.malId

        if (identif != -1) {
            viewModel.fetchAnimeById(identif)
        }

        onClickBackHandle()
        viewModel.state.observe(viewLifecycleOwner) {state ->
            binding.textViewTitle.text = state.anime?.title ?: "Anime no encontrado"
            binding.textViewSynopsis.text = state.anime?.synopsis ?: ""
            binding.textScoreNumeric.text = state.anime?.score.toString()

            Glide.with(this)
                .load(Uri.parse(state.anime?.imageUri ?: "https://media.licdn.com/dms/image/v2/C4D03AQFx78pL_Khnvg/profile-displayphoto-shrink_200_200/profile-displayphoto-shrink_200_200/0/1619538082475?e=2147483647&v=beta&t=2CM9ps2IvLsXzWTV3zY85T4g6bak_7AaEO1vhO5JsSQ"))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.chikojuapo)
                .error(R.drawable.gato)
                .centerCrop()
                .into(binding.imageCover)
        }
    }

    private fun onClickBackHandle() {
        requireActivity().onBackPressedDispatcher.addCallback {
            findNavController().navigateUp()
        }
    }
}