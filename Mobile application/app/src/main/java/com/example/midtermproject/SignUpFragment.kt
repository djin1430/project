package com.example.midtermproject

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.midtermproject.databinding.FragmentSignUpBinding




class SignUpFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val binding = DataBindingUtil.inflate<FragmentSignUpBinding>(inflater,
            R.layout.fragment_sign_up,container,false)

        binding.signup1.setOnClickListener{view: View ->
            if (binding.firstname.text.toString().equals("") || binding.lastname.text.toString().equals("") || binding.childfname.text.toString().equals("")
                || binding.childlname.text.toString().equals("") || binding.username.text.toString().equals("") || binding.password.text.toString().equals("")
                || binding.contact.text.toString().equals(""))
            {
                Toast.makeText(requireActivity(), "Please Complete the Form", Toast.LENGTH_SHORT).show();

            }
            else {
                val mAlertDialog = AlertDialog.Builder(activity)
                mAlertDialog.setTitle("SignUp")
                mAlertDialog.setIcon(R.drawable.ic_menu)
                mAlertDialog.setMessage("No Changes?")
                mAlertDialog.setPositiveButton("Yes"){
                        Dialog,which -> Toast.makeText(requireActivity(), "Signup Success", Toast.LENGTH_SHORT).show();
                    view.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
                }
                mAlertDialog.setNegativeButton("No"){
                        Dialog, which ->
                }

                val createBuild = mAlertDialog.create()
                createBuild.show()

            }
        }
        binding.btnCancel.setOnClickListener{view: View -> view.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)}
        return binding.root
    }
}