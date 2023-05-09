package com.menesdurak.appterncasestudy.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.menesdurak.appterncasestudy.adapter.ArtistAdapter
import com.menesdurak.appterncasestudy.databinding.FragmentArtistsBinding
import com.menesdurak.appterncasestudy.viewmodel.DeezerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistsFragment : Fragment() {

    private var _binding: FragmentArtistsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            defaultViewModelProviderFactory
        )[DeezerViewModel::class.java]
    }

    private var genreName: String = ""
    private var genreId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistsBinding.inflate(inflater, container, false)
        val view = binding.root

        //Receiving arguments
        val args: ArtistsFragmentArgs by navArgs()
        genreName = args.genreName
        genreId = args.genreId

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getArtists(genreId)

        viewModel.artistList.observe(viewLifecycleOwner) {artist ->
            binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
            val artistAdapter = ArtistAdapter(artist.data){artistData ->
                val action = ArtistsFragmentDirections.actionArtistsFragmentToAlbumsFragment(
                    artistName = artistData.name,
                    artistId = artistData.id,
                    artistBigPictureLink = artistData.picture_big
                )
                findNavController().navigate(action)
            }
            binding.recyclerView.adapter = artistAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}