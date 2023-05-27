package com.example.bootcamp27may.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bootcamp27may.adapter.ProductAdapter
import com.example.bootcamp27may.api.ApiUtils
import com.example.bootcamp27may.databinding.FragmentHomeBinding
import com.example.bootcamp27may.model.ProductResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val productAdapter = ProductAdapter()
    private val api = ApiUtils.getProductApi()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        getProduct()

    }

    private fun setRecyclerView() {
        binding.rvProduct.layoutManager = LinearLayoutManager(context)
        binding.rvProduct.adapter = productAdapter
    }

    private fun getProduct() {
        api.getAllProductApi().enqueue(object : Callback<List<ProductResponseItem>> {

            override fun onResponse(
                call: Call<List<ProductResponseItem>>,
                response: Response<List<ProductResponseItem>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { productAdapter.updateList(it) }
                }
            }

            override fun onFailure(call: Call<List<ProductResponseItem>>, t: Throwable) {

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}