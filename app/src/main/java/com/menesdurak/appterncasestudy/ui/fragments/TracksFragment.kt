package com.menesdurak.appterncasestudy.ui.fragments

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.menesdurak.appterncasestudy.adapter.TrackAdapter
import com.menesdurak.appterncasestudy.data.model.FavoriteTrack
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

    private val mediaPlayer: MediaPlayer = MediaPlayer()

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

        viewModel.getAllFavoriteTrackIds()
        viewModel.getTracks(albumId)

        viewModel.trackList.observe(viewLifecycleOwner) { track ->
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            val albumAdapter = TrackAdapter(track.data, albumImageLink)
            binding.recyclerView.adapter = albumAdapter
            albumAdapter.setOnFavoriteClickListener(object : TrackAdapter.OnFavoriteClickListener {
                override fun onFavoriteClick(position: Int) {
                    val favoriteTrack =
                        FavoriteTrack(
                            track.data[position].id,
                            track.data[position].title,
                            track.data[position].duration,
                            albumImageLink,
                            track.data[position].preview
                        )
                    viewModel.favoriteTrackIdList.observe(viewLifecycleOwner) { favoriteTrackIdList ->
                        if (favoriteTrack.remoteId !in favoriteTrackIdList) {
                            viewModel.addFavoriteTrack(favoriteTrack)
                        } else {
                            viewModel.deleteFavoriteTrackWithId(favoriteTrack.remoteId)
                        }
                    }
                }

            })
            albumAdapter.setOnPlayClickListener(object : TrackAdapter.OnPlayClickListener {
                override fun onPlayClick(position: Int) {
                    if (!mediaPlayer.isPlaying) {
                        playTrack(mediaPlayer, track.data[position].preview)
                    } else {
                        mediaPlayer.stop()
                        mediaPlayer.reset()
                    }
                }

            })

        }

    }

    private fun playTrack(mediaPlayer: MediaPlayer, trackUrl: String) {
        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
        )
        mediaPlayer.setDataSource(trackUrl)
        mediaPlayer.prepare()
        mediaPlayer.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}