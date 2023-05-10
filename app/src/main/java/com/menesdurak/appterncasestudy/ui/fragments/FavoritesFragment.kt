package com.menesdurak.appterncasestudy.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.menesdurak.appterncasestudy.adapter.FavoriteTrackAdapter
import com.menesdurak.appterncasestudy.data.model.FavoriteTrack
import com.menesdurak.appterncasestudy.databinding.FragmentFavoritesBinding
import com.menesdurak.appterncasestudy.viewmodel.DeezerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
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
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllFavoriteTracks()
//        viewModel.getAllFavoriteTrackIds()
//        val favoriteTrack = FavoriteTrack(
//            3135556,
//            "Harder, Better, Faster, Stronger",
//            224,
//            "https://e-cdns-images.dzcdn.net/images/cover/2e018122cb56986277102d2041a592c8/250x250-000000-80-0-0.jpg",
//            "https://cdns-preview-d.dzcdn.net/stream/c-deda7fa9316d9e9e880d2c6207e92260-10.mp3"
//        )
//        viewModel.addFavoriteTrack(favoriteTrack)
//        viewModel.favoriteTrackList.observe(viewLifecycleOwner) {
//            println(it[0].name + it[0].remoteId)
//        }
//        viewModel.favoriteTrackIdList.observe(viewLifecycleOwner) {
//            println(it[0])
//        }
        
        viewModel.favoriteTrackList.observe(viewLifecycleOwner) { favoriteTrackList ->
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            val adapter = FavoriteTrackAdapter(favoriteTrackList)
            binding.recyclerView.adapter = adapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}