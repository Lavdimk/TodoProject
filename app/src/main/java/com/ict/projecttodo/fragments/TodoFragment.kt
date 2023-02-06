package com.ict.projecttodo.fragments

import android.content.ContentValues.TAG
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ict.projecttodo.adapter.PostListAdapter
import com.ict.projecttodo.databinding.FragmentTodoBinding
import com.ict.projecttodo.models.Post
import com.ict.projecttodo.ui.fragments.api.ServiceAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TodoFragment : Fragment() {
    private lateinit var binding: FragmentTodoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=FragmentTodoBinding.inflate(inflater,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTodoFragment.setOnClickListener {
            makeAPICalls()
        }
    }

    private fun makeAPICalls() {
      val retrofitInstance=Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create())
          .baseUrl("https://jsonplaceholder.typicode.com/")
          .build()
          .create(ServiceAPI::class.java)

        val getAllPost=retrofitInstance.getAllPosts()
        getAllPost.enqueue(object : Callback<List<Post>?> {
            override fun onResponse(call: Call<List<Post>?>, response: Response<List<Post>?>) {
                val listOfPosts = response.body()


                binding.listView.adapter = listOfPosts?.let { context?.let { it1 ->
                    PostListAdapter(
                        it1, it)
                } }
                binding.listView.setOnItemClickListener { adapterView, view, i, l ->

                    val post= listOfPosts?.get(i)
                    if (post != null) {
                        if (!post.completed){
                            Toast.makeText(requireContext(),"Kjo todo nuk mund te ruhet ne sharedpreferences sepse e ka vleren e completed: false.",Toast.LENGTH_LONG).show()
                        }else{
                            val title=listOfPosts[i].title
                            val sharedPreferences = requireActivity().getSharedPreferences("MyPreferences", MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString("key", title)
                            editor.apply()
                            Toast.makeText(requireContext(),"Titulli u ruajt ne Saved Todos",Toast.LENGTH_LONG).show()

                        }
                    }
                }


            }
            override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }
//    val title=listOfPosts[i].title
//    if (title != null) {
//        putStringOnSharedPrefs(requireContext(), "title", title)
//    }

}