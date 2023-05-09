package com.menesdurak.appterncasestudy.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.menesdurak.appterncasestudy.adapter.TrackAdapter
import com.menesdurak.appterncasestudy.databinding.FragmentTracksBinding
import com.menesdurak.appterncasestudy.viewmodel.DeezerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TracksFragment : Fragment() {
    private var _binding: FragmentTracksBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            defaultViewModelProviderFactory
        )[DeezerViewModel::class.java]
    }

    private var albumName: String = ""
    private var albumId: Int = -1
    private var albumImageLink: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTracksBinding.inflate(inflater, container, false)
        val view = binding.root

        //Receiving arguments
        val args: TracksFragmentArgs by navArgs()
        albumName = args.albumName
        albumId = args.albumId
        albumImageLink = args.albumImageLink
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTracks(albumId)

        viewModel.trackList.observe(viewLifecycleOwner) {track ->
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            val albumAdapter = TrackAdapter(track.data, albumImageLink)
            binding.recyclerView.adapter = albumAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}