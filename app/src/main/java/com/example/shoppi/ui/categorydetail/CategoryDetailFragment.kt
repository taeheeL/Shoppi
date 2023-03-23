package com.example.shoppi.ui.categorydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.example.shoppi.common.KEY_CATEGORY_LABEL
import com.example.shoppi.databinding.FragmentCategoryDetailBinding
import com.example.shoppi.repository.categorydetail.CategoryDetailViewModel
import com.example.shoppi.ui.common.ProductClickListener
import com.example.shoppi.ui.common.ViewModelFactory

class CategoryDetailFragment : Fragment(), ProductClickListener {

    private lateinit var binding: FragmentCategoryDetailBinding
    private val viewModel: CategoryDetailViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        setToolbar()
        setListAdapter()

    }

    private fun setToolbar() {
        val categoryLabel = requireArguments().getString(KEY_CATEGORY_LABEL)
        binding.toolbarCategoryDetail.title = categoryLabel
    }


    private fun setListAdapter() {
        val topSellingSectionAdapter = CategoryTopSellingSectionAdapter()
        val titleAdapter = SectionTitleAdaptor()
        val promotionAdapter = ProductPromotionAdaptor(this)
        binding.rvCategoryDetail.adapter =
            ConcatAdapter(topSellingSectionAdapter, titleAdapter, promotionAdapter)
        viewModel.topSelling.observe(viewLifecycleOwner) { topSelling ->
            topSellingSectionAdapter.submitList(listOf(topSelling))
        }

        viewModel.promotions.observe(viewLifecycleOwner) { promotions ->
            titleAdapter.submitList(listOf(promotions.title))
            promotionAdapter.submitList(promotions.items)
        }

    }

    override fun onProductClick(productId: String) {
        TODO("Not yet implemented")
    }

}