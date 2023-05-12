package com.menesdurak.appterncasestudy.ui.fragments

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.menesdurak.appterncasestudy.adapter.FavoriteTrackAdapter
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

    private val mediaPlayer: MediaPlayer = MediaPlayer()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllFavoriteTracks()

        viewModel.favoriteTrackList.observe(viewLifecycleOwner) { favoriteTrackList ->
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            val adapter = FavoriteTrackAdapter()
            binding.recyclerView.adapter = adapter
            adapter.updateList(favoriteTrackList)
            adapter.setOnPlayClickListener(object : FavoriteTrackAdapter.OnPlayClickListener {
                override fun onPlayClick(position: Int) {
                    if (!mediaPlayer.isPlaying) {
                        playTrack(mediaPlayer, favoriteTrackList[position].previewLink)
                    } else {
                        mediaPlayer.stop()
                        mediaPlayer.reset()
                    }
                }
            })
            adapter.setOnFavoriteClickListener(object :
                FavoriteTrackAdapter.OnFavoriteClickListener {
                override fun onFavoriteClick(position: Int) {
                    viewModel.deleteFavoriteTrackWithId(favoriteTrackList[position].id)
                    adapter.updateList(favoriteTrackList)
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