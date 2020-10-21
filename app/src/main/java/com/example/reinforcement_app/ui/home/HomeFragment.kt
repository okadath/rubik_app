package com.example.reinforcement_app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.reinforcement_app.R
import kotlinx.android.synthetic.main.fragment_home.*
import androidx.databinding.DataBindingUtil


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        button_tuto.setText("asd")
//        button_tuto?.setOnClickListener {v -> Navigation.findNavController(v).navigate(R.id.action_nav_home_to_tuto1) }

//        button_tuto.setOnClickListener { view : View ->
//            view.findNavController().navigate(R.id.action_nav_home_to_tuto12)
//        }
//        button_IL.setOnClickListener { view : View ->
//            view.findNavController().navigate(R.id.action_nav_home_to_nav_IL2)
//        }
//        button_tuto.setOnClickListener {v -> Navigation.findNavController(v).navigate(R.id.action_nav_home_to_tuto1) }
//        button_IL.setOnClickListener {v -> Navigation.findNavController(v).navigate(R.id.action_nav_home_to_nav_IL) }


    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {



        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

//        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
//            R.layout.fragment_title,container,false)
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.button_tuto).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_nav_home_to_tuto1)
        )
        view.findViewById<View>(R.id.button_IL).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_nav_home_to_nav_IL)
        )
        view.findViewById<View>(R.id.button_settings).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_nav_home_to_settingsFragment)
        )
    }
}