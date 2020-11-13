package com.haroldcalayan.feature.master

import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haroldcalayan.BR
import com.haroldcalayan.R
import com.haroldcalayan.common.base.BaseActivity
import com.haroldcalayan.databinding.ActivityMasterBinding
import com.haroldcalayan.feature.detail.DetailActivity
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [DetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class MasterActivity : BaseActivity<ActivityMasterBinding, MasterViewModel>() {

  private lateinit var adapter: MovieHeaderAdapter

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

  override fun subscribe() {
    super.subscribe()
    getViewModel().movies.observe(this, Observer {
      adapter.updateList(it)
    })
  }

  private fun setupRecyclerView(recyclerView: RecyclerView) {
    val gridLayoutManager = GridLayoutManager(this, 2)
    gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {

      override fun getSpanSize(position: Int) : Int {
        return (if ((position % 3) == 0) 2 else 1)
      }
    }
    recyclerView.layoutManager = LinearLayoutManager(this)
    adapter = MovieHeaderAdapter(this, emptyList(), twoPane)
    recyclerView.adapter = adapter
    recyclerView.addItemDecoration(StickyRecyclerHeadersDecoration(adapter))
  }
}