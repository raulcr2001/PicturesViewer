package com.example.picturesviewer

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.picturesviewer.adapters.AdaptadorCards
import com.example.picturesviewer.adapters.Card
import com.example.picturesviewer.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = binding.cardRv
        val cardAdapter = AdaptadorCards(
            arrayListOf(
                Card(R.drawable.image1),
                Card(R.drawable.image2),
                Card(R.drawable.image3),
                Card(R.drawable.image4),
                Card(R.drawable.image5),
                Card(R.drawable.image6),
                Card(R.drawable.image7),
                Card(R.drawable.image8),
            )
        )


        var modeCallBack: ActionMode.Callback = object : ActionMode.Callback {
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                val id = item?.itemId
                when (id) {
                    R.id.action_editar -> {
                        Log.i("MainActivity", "editar")
                        mode?.finish()
                    }
                    R.id.action_eliminar -> {
                        Log.i("MainActivity", "eliminar")
                        mode?.finish()
                    }
                    R.id.action_compartir -> {
                        Log.i("MainActivity", "compartir")
                        mode?.finish()
                    }
                    else -> return false
                }
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                var mode = mode
                mode = null
            }

            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                mode.setTitle("Options")
                mode.getMenuInflater().inflate(R.menu.menu_context, menu)
                return true
            }
        }
        rv.adapter = cardAdapter
        rv.layoutManager = GridLayoutManager(context, 2)
        cardAdapter.onLongClick = { it ->
            it.startActionMode(modeCallBack)
        }
        //Back button not working for some reason...
        cardAdapter.onClick = { view, card ->
            findNavController().navigate(
                R.id.goto_secondFragment,
                bundleOf("card" to card.foto)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}