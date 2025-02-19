package com.example.appejemplo.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appejemplo.adapters.AnimeAdapter
import com.example.appejemplo.adapters.AnimeOnClickDetail
import com.example.appejemplo.databinding.FragmentAnimesBinding
import com.example.appejemplo.viewmodels.AnimeViewModel


class AnimesFragment : Fragment(), AnimeOnClickDetail {
    private lateinit var binding: FragmentAnimesBinding
    private lateinit var mAdapter: AnimeAdapter
    private lateinit var mLayoutManager: GridLayoutManager

    private val viewModel : AnimeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecyclerView() {
        mAdapter = AnimeAdapter(mutableListOf(), this)
        mLayoutManager = GridLayoutManager(requireContext(), 2)

        binding.recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }

        binding.btnSearch.setOnClickListener {
            viewModel.searchAnimeByName(binding.editTextSearch.text.toString())
        }
        binding.editTextSearch.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                viewModel.searchAnimeByName(binding.editTextSearch.text.toString())
                return@OnKeyListener true
            }
            false
        })

        viewModel.state.observe(viewLifecycleOwner) {state ->
            binding.progressBar.visibility = if (state.loading) View.VISIBLE else View.GONE
            mAdapter.listAnimes = state.list
            mAdapter.notifyDataSetChanged()
        }

    }

    override fun onClickAnime(id: Int) {
        val action = AnimesFragmentDirections.actionAnimesFragmentToAnimeDetailFragment(id)
        findNavController().navigate(action)
    }
}