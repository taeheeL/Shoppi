package com.example.shoppi.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.shoppi.R
import com.example.shoppi.common.KEY_PRODUCT_ID
import com.example.shoppi.databinding.FragmentHomeBinding
import com.example.shoppi.ui.categorydetail.ProductPromotionAdaptor
import com.example.shoppi.ui.categorydetail.SectionTitleAdaptor
import com.example.shoppi.ui.common.EventObserver
import com.example.shoppi.ui.common.ProductClickListener
import com.example.shoppi.ui.common.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(), ProductClickListener {

    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        setToolbar()
        setNavigation()
        setTopBanners()
        setListAdapter()

    }

    private fun setToolbar() {
        viewModel.title.observe(viewLifecycleOwner) { title ->
            binding.title = title
        }
    }

    private fun setNavigation() {
        viewModel.openProductEvent.observe(viewLifecycleOwner, EventObserver { productId ->
            findNavController().navigate(
                R.id.action_home_to_product_detail, bundleOf(
                    KEY_PRODUCT_ID to productId
                )
            )
        })
    }

    private fun setTopBanners() {
        with(binding.viewpagerHomeBanner) {
            adapter = HomeBannerAdapter(viewModel).apply {
                viewModel.topBanners.observe(viewLifecycleOwner) { banners ->
                    submitList(banners)
                }
            }
            val pageWidth = resources.getDimension(R.dimen.viewpager_item_with)
            val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)
            val screenWidth = resources.displayMetrics.widthPixels
            val offset = screenWidth - pageWidth - pageMargin

            offscreenPageLimit = 3
            setPageTransformer { page, position ->
                page.translationX = position * -offset
            }

            TabLayoutMediator(binding.viewpagerHomeBannerIndicator, this) { tab, position ->

            }.attach()
        }
    }

    private fun setListAdapter() {
        val titleAdapter = SectionTitleAdaptor()
        val promotionAdapter = ProductPromotionAdaptor(this)
        binding.rvHome.adapter = ConcatAdapter(titleAdapter, promotionAdapter)
        viewModel.promotions.observe(viewLifecycleOwner) { promotions ->
            titleAdapter.submitList(listOf(promotions.title))
            promotionAdapter.submitList(promotions.items)
        }
    }

    override fun onProductClick(productId: String) {
        findNavController().navigate(
            R.id.action_home_to_product_detail, bundleOf(
                KEY_PRODUCT_ID to "desk-1"
            )
        )
    }


}
