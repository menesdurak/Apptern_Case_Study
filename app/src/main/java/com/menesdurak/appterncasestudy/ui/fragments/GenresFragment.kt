package com.menesdurak.appterncasestudy.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.menesdurak.appterncasestudy.adapter.GenreAdapter
import com.menesdurak.appterncasestudy.databinding.FragmentGenresBinding
import com.menesdurak.appterncasestudy.viewmodel.DeezerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenresFragment : Fragment() {

    private var _binding: FragmentGenresBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DeezerViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenresBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getGenres()

        viewModel.genreList.observe(viewLifecycleOwner) {genre ->
            binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
            val genreAdapter = GenreAdapter(genre.data) {genreData ->
                val action = GenresFragmentDirections.actionGenresFragmentToArtistsFragment(
                    genreName = genreData.name,
                    genreId = genreData.id
                )
                findNavController().navigate(action)
            }
            binding.recyclerView.adapter = genreAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}