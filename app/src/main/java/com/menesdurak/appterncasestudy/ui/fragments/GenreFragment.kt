package com.menesdurak.appterncasestudy.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.menesdurak.appterncasestudy.adapter.GenreAdapter
import com.menesdurak.appterncasestudy.databinding.FragmentGenreBinding
import com.menesdurak.appterncasestudy.viewmodel.DeezerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreFragment : Fragment() {

    private var _binding: FragmentGenreBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            defaultViewModelProviderFactory
        )[DeezerViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenreBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getGenres()

        viewModel.genreList.observe(viewLifecycleOwner) {
            binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
            val genreAdapter = GenreAdapter(it.data)
            binding.recyclerView.adapter = genreAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}