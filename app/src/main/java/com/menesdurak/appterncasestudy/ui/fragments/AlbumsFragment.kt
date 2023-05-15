package com.menesdurak.appterncasestudy.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.menesdurak.appterncasestudy.R
import com.menesdurak.appterncasestudy.adapter.AlbumAdapter
import com.menesdurak.appterncasestudy.databinding.FragmentAlbumsBinding
import com.menesdurak.appterncasestudy.viewmodel.DeezerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsFragment : Fragment() {
    private var _binding: FragmentAlbumsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DeezerViewModel by viewModels()

    private var artistName: String = ""
    private var artistBigPictureLink: String = ""
    private var artistId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumsBinding.inflate(inflater, container, false)
        val view = binding.root

        //Receiving arguments
        val args: AlbumsFragmentArgs by navArgs()
        artistName = args.artistName
        artistId = args.artistId
        artistBigPictureLink = args.artistBigPictureLink

        Glide
            .with(requireContext())
            .load(artistBigPictureLink)
            .centerCrop()
            .placeholder(R.drawable.loading)
            .into(binding.ivArtistDetail)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvArtistName.text = artistName

        viewModel.getAlbums(artistId)

        viewModel.albumList.observe(viewLifecycleOwner) { album ->
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            val albumAdapter = AlbumAdapter(album.data) {albumData ->
                val action = AlbumsFragmentDirections.actionAlbumsFragmentToTracksFragment(
                    albumId = albumData.id,
                    albumName = albumData.title,
                    albumImageLink = albumData.cover_medium
                )
                findNavController().navigate(action)
            }
            binding.recyclerView.adapter = albumAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}