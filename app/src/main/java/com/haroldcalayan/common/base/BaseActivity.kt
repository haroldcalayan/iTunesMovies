package com.haroldcalayan.common.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.haroldcalayan.BR
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    private lateinit var viewDataBinding: T
    private var viewModel: V? = null

    /**
     * @return layout res
     */
    abstract fun getLayout(): Int

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    open fun getBindingVariable(): Int = BR.viewModel

    /**
     * Initialize all data related task
     */
    open fun initData() {}

    /**
     * Initialize all view related task
     */
    open fun initViews() {}

    /**
     * Subscribe to live data of view model
     */
    open fun subscribe() {}

    /**
     * Return data binding of
     * the current Activity
     */
    fun getBinding(): T {
        return viewDataBinding
    }

    fun getViewModel(): V {
        return viewModel!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        initViews()
        initData()
        subscribe()
    }

    private fun provideViewModel() {
        val clazz: Class<V> = getViewModelClass()
        viewModel = ViewModelProviders.of(this).get(clazz)
    }

    private fun getViewModelClass(): Class<V> {
        return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<V>
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayout())
        provideViewModel()
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
    }

    fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                or View.SYSTEM_UI_FLAG_IMMERSIVE)
    }

    fun showSnackBarMessage(view: View, stringId: Int) {
        Snackbar.make(view, stringId, Snackbar.LENGTH_SHORT).apply {
            show()
        }
    }

    fun showToastMessage(stringId: Int) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show()
    }
}