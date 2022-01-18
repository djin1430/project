package com.example.midtermproject
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.midtermproject.databinding.FragmentLoginBinding
import java.text.NumberFormat


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater,
            R.layout.fragment_login,container,false)

        binding.login.setOnClickListener{view: View ->
            if (binding.username.text.toString().equals("admin") && binding.password.text.toString().equals("123"))
            {
                view.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
            else {Toast.makeText(requireActivity(), "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
            }
        }
        binding.signup.setOnClickListener{view: View ->
            view.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)

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