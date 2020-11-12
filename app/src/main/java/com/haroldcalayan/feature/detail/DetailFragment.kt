package com.haroldcalayan.feature.detail

import android.os.Bundle
import android.view.View
import com.haroldcalayan.BR
import com.haroldcalayan.R
import com.haroldcalayan.common.base.BaseFragment
import com.haroldcalayan.databinding.FragmentDetailBinding
import com.haroldcalayan.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [DetailActivity]
 * on handsets.
 */
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

  private var item: DummyContent.DummyItem? = null

  override fun getLayout() = R.layout.fragment_detail

  override fun getBindingVariable() = BR.viewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      if (it.containsKey(ARG_ITEM_ID)) {
        item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
        toolbar_layout?.title = item?.content
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    item?.let {
      item_detail.text = it.details
    }
  }

  companion object {
    const val ARG_ITEM_ID = "item_id"
  }
}