package com.haroldcalayan.feature.detail

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import android.view.MenuItem
import com.haroldcalayan.BR
import com.haroldcalayan.R
import com.haroldcalayan.common.base.BaseActivity
import com.haroldcalayan.databinding.ActivityDetailBinding
import com.haroldcalayan.feature.master.MasterActivity

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [MasterActivity].
 */
class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

  override fun getLayout() = R.layout.activity_detail

  override fun getBindingVariable() = BR.viewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setSupportActionBar(findViewById(R.id.detail_toolbar))

    findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
      Snackbar.make(view, "This feature will be available soon.", Snackbar.LENGTH_LONG)
        .setAction("Action", null)
        .show()
    }

    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    // savedInstanceState is non-null when there is fragment state
    // saved from previous configurations of this activity
    // (e.g. when rotating the screen from portrait to landscape).
    // In this case, the fragment will automatically be re-added
    // to its container so we don"t need to manually add it.
    // For more information, see the Fragments API guide at:
    //
    // http://developer.android.com/guide/components/fragments.html
    //
    if (savedInstanceState == null) {
      // Create the detail fragment and add it to the activity
      // using a fragment transaction.
      val fragment = DetailFragment().apply {
        arguments = Bundle().apply {
          putString(
            DetailFragment.ARG_ITEM,
            intent.getStringExtra(DetailFragment.ARG_ITEM)
          )
        }
      }

      supportFragmentManager.beginTransaction()
        .add(R.id.item_detail_container, fragment)
        .commit()
    }
  }

  override fun onOptionsItemSelected(item: MenuItem) =
    when (item.itemId) {
      android.R.id.home -> {
        //navigateUpTo(Intent(this, MasterActivity::class.java))
        finish()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
}