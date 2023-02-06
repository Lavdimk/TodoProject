package com.ict.projecttodo.fragments

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ict.projecttodo.databinding.FragmentSavedTodosDataBinding

class SavedTodosDataFragment : Fragment() {
    private lateinit var binding: FragmentSavedTodosDataBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSavedTodosDataBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btngetSavedTodo.setOnClickListener {
            val sharedPreferences = requireActivity().getSharedPreferences("MyPreferences", MODE_PRIVATE)
            val savedValue = sharedPreferences.getString("key", "")
            binding.etsavedTodo.setText(savedValue)


        }
    }

}