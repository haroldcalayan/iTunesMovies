package com.haroldcalayan.feature.detail

import android.os.Bundle
import android.view.View
import com.haroldcalayan.BR
import com.haroldcalayan.R
import com.haroldcalayan.common.base.BaseFragment
import com.haroldcalayan.data.model.Movie
import com.haroldcalayan.databinding.FragmentDetailBinding
import com.haroldcalayan.utils.DateUtils
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
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    getBinding().movie = item
    item?.let {
      var movieInfo = StringBuilder()
      movieInfo.apply {
        append("Track ID: ")
        append(it.trackId)
        append("\n")
        append("Track Name: ")
        append(it.trackName)
        append("\n")
        append("Track Price: ")
        append(it.currency)
        append(" ")
        append(it.trackPrice)
        append("\n")
        append("Collection ID: ")
        append(it.collectionId)
        append("\n")
        append("Collection Name: ")
        append(it.collectionName)
        append("\n")
        append("Collection Price: ")
        append(it.currency)
        append(" ")
        append(it.collectionPrice)
        append("\n")
        append("Kind: ")
        append(it.kind)
        append("\n")
        append("Artist Name: ")
        append(it.artistName)
        append("\n")
        append("Country: ")
        append(it.country)
        append("\n")
        append("Release Date: ")
        append(it.releaseDate)
        append("\n")
      }
      item_detail.text = movieInfo
    }

    Timber.d("today: " + DateUtils.today())
  }

  companion object {
    const val ARG_ITEM = "item"
  }
}