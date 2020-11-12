package com.haroldcalayan.feature.master

import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.haroldcalayan.BR
import com.haroldcalayan.R
import com.haroldcalayan.common.base.BaseActivity
import com.haroldcalayan.databinding.ActivityMasterBinding
import com.haroldcalayan.dummy.DummyContent
import com.haroldcalayan.feature.detail.DetailActivity

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [DetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class MasterActivity : BaseActivity<ActivityMasterBinding, MasterViewModel>() {

  /**
   * Whether or not the activity is in two-pane mode, i.e. running on a tablet
   * device.
   */
  private var twoPane: Boolean = false

  override fun getLayout() = R.layout.activity_master

  override fun getBindingVariable() = BR.viewModel

  override fun initViews() {
    super.initViews()

    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)
    toolbar.title = title

    if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
      // The detail container view will be present only in the
      // large-screen layouts (res/values-w900dp).
      // If this view is present, then the
      // activity should be in two-pane mode.
      twoPane = true
    }

    setupRecyclerView(findViewById(R.id.item_list))
  }

  private fun setupRecyclerView(recyclerView: RecyclerView) {
    recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, twoPane)
  }
}