package com.haroldcalayan.feature.detail

import android.os.Bundle
import android.view.View
import com.haroldcalayan.BR
import com.haroldcalayan.R
import com.haroldcalayan.common.base.BaseFragment
import com.haroldcalayan.data.model.Movie
import com.haroldcalayan.databinding.FragmentDetailBinding
import com.haroldcalayan.utils.JsonUtils
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_detail.*
import timber.log.Timber

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [DetailActivity]
 * on handsets.
 */
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

  private var item: Movie? = null

  override fun getLayout() = R.layout.fragment_detail

  override fun getBindingVariable() = BR.viewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      if (it.containsKey(ARG_ITEM)) {
        item = JsonUtils.getGson().fromJson(it.getString(ARG_ITEM), Movie::class.java)
        activity?.toolbar_layout?.title = item?.trackName
        Timber.d("item: $item")
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    getBinding().movie = item
    item?.let {
      var movieInfo = String().apply {
        plus("Track ID: ").plus(it.trackId).plus("\n")
        plus("Track Name: ").plus(it.trackName).plus("\n")
        plus("Track Price: ").plus(it.currency).plus(" ").plus(it.trackPrice).plus("\n")
        plus("Collection ID: ").plus(it.collectionId).plus("\n")
        plus("Collection Name: ").plus(it.collectionName).plus("\n")
        plus("Collection Price: ").plus(it.currency).plus(" ").plus(it.collectionPrice).plus("\n")
        plus("Kind: ").plus(it.kind).plus("\n")
        plus("Artist Name: ").plus(it.artistName).plus("\n")
        plus("Country: ").plus(it.country).plus("\n")
        plus("Release Date: ").plus(it.releaseDate).plus("\n")
      }
      Timber.d("movieInfo: $movieInfo")
      item_detail.text = movieInfo
    }
  }

  companion object {
    const val ARG_ITEM = "item"
  }
}