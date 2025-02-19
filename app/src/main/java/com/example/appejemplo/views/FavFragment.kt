package com.example.appejemplo.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appejemplo.App
import com.example.appejemplo.adapters.AnimeAdapter
import com.example.appejemplo.adapters.AnimeOnClickDetail
import com.example.appejemplo.databinding.FragmentFavBinding
import com.example.appejemplo.viewmodels.FavViewModel
import com.example.appejemplo.viewmodels.FavViewModelFactory


class FavFragment : Fragment(), AnimeOnClickDetail {
    private lateinit var binding: FragmentFavBinding
    private lateinit var mAdapter: AnimeAdapter
    private lateinit var mLayoutManager: GridLayoutManager

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
        setupRecyclerView()
        onClickBackHandle()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecyclerView() {
        mAdapter = AnimeAdapter(mutableListOf(), this)
        mLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }

        viewModel.state.observe(viewLifecycleOwner) {state ->
            mAdapter.listAnimes = state.list
            mAdapter.notifyDataSetChanged()
        }
    }

    fun deleteContact(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickAnime(id: Int) {
        val action = FavFragmentDirections.actionFavFragmentToAnimeDetailFragment(id)
        findNavController().navigate(action)
    }

    private fun onClickBackHandle() {
        requireActivity().onBackPressedDispatcher.addCallback {
            findNavController().navigateUp()
        }
    }

}