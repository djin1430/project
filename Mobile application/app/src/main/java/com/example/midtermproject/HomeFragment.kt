package com.example.midtermproject

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast

import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.midtermproject.databinding.FragmentHomeBinding



class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home,container,false)

        binding.btnExit.setOnClickListener{view: View ->

            val mAlertDialog = AlertDialog.Builder(activity)
            mAlertDialog.setTitle("Exit")
            mAlertDialog.setIcon(R.drawable.ic_menu)
            mAlertDialog.setMessage("Are you sure you want to exit?")
            mAlertDialog.setPositiveButton("Yes"){
                Dialog,which -> view.findNavController().navigate(R.id.action_homeFragment_to_loginFragment2)
            }
            mAlertDialog.setNegativeButton("No"){
                Dialog, which -> Toast.makeText(requireActivity(), "Use this button again to exit", Toast.LENGTH_SHORT).show();
            }

            val createBuild = mAlertDialog.create()
            createBuild.show()

        }
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}