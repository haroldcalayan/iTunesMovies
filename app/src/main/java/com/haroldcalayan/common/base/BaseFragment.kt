package com.haroldcalayan.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.haroldcalayan.BR
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {

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
    abstract fun getBindingVariable(): Int

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

    private fun provideViewModel() {
        val clazz: Class<V> = getViewModelClass()
        viewModel = ViewModelProviders.of(this).get(clazz)
    }

    private fun getViewModelClass(): Class<V> {
        return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<V>
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        performDataBinding(inflater, container)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
        subscribe()
    }

    private fun performDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        provideViewModel()
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
    }
}