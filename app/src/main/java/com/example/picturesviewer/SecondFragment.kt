package com.example.picturesviewer

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.fragment.findNavController
import com.example.picturesviewer.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }
    var isPlaying = false;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageResource = arguments?.getInt("card") ?: R.drawable.ic_launcher_background
        binding.imageView.setImageResource(imageResource)

        binding.floatingActionButton.setOnClickListener {
            val anim = if (isPlaying){
                AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.ic_play_to_pause
                ) as AnimatedVectorDrawable
            } else{
                AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.ic_pause_to_play
                ) as AnimatedVectorDrawable
            }
            (it as ImageView).setImageDrawable(anim)
            anim.start()
            isPlaying = !isPlaying
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}