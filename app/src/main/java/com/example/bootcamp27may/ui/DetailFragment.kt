package com.example.bootcamp27may.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.bootcamp27may.api.ApiUtils
import com.example.bootcamp27may.databinding.FragmentDetailBinding
import com.example.bootcamp27may.model.ProductResponseItem
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private val api = ApiUtils.getProductApi()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProductSingleData()
    }

    private fun getProductSingleData() {
        binding.progressBar.visibility = View.VISIBLE
        val id = args.productId
        api.getSingleProduct(id).enqueue(object : Callback<ProductResponseItem> {
            override fun onResponse(
                call: Call<ProductResponseItem>,
                response: Response<ProductResponseItem>
            ) {
                if (response.isSuccessful) {
                    binding.product = response.body()
                    Picasso.get().load(response.body()?.image).into(binding.imageViewDetail)
                }
                binding.progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<ProductResponseItem>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}