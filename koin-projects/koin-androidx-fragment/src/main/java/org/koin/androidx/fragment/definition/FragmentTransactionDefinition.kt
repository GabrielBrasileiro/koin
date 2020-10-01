package org.koin.androidx.fragment.definition

import android.os.Bundle
import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import org.koin.androidx.fragment.android.getFragment
import org.koin.androidx.fragment.android.replace
import org.koin.core.KoinExperimentalAPI

/**
 * @author Gabriel Brasileiro
 */
data class FragmentTransactionDefinition(
   @PublishedApi internal val fragmentManager: FragmentManager,
   @PublishedApi internal val transactionManager: FragmentTransaction
) {

    /**
     * Replace container
     *
     * @param containerViewId
     * @param args
     * @param tag
     */
    @KoinExperimentalAPI
    inline fun <reified F : Fragment> replace(
        @IdRes containerViewId: Int,
        args: Bundle? = null,
        tag: String? = null
    ): FragmentTransaction {
        return transactionManager.replace<F>(containerViewId, args, tag)
    }

    /**
     * Add fragment to container
     *
     * @param containerViewId
     * @param args
     * @param tag
     */
    @KoinExperimentalAPI
    inline fun <reified F : Fragment> add(
        @IdRes containerViewId: Int,
        args: Bundle? = null,
        tag: String? = null
    ) {
        transactionManager.add(containerViewId, F::class.java, args, tag)
    }

    /**
     * Show fragment of stack
     */
    @KoinExperimentalAPI
    inline fun <reified F : Fragment> show() {
        val fragment = fragmentManager.getFragment<F>()
        transactionManager.show(fragment)
    }

    /**
     * Attach fragment of stack
     */
    @KoinExperimentalAPI
    inline fun <reified F : Fragment> attach() {
        val fragment = fragmentManager.getFragment<F>()
        transactionManager.attach(fragment)
    }

    /**
     * Remove fragment of stack
     */
    @KoinExperimentalAPI
    inline fun <reified F : Fragment> remove() {
        val fragment = fragmentManager.getFragment<F>()
        transactionManager.remove(fragment)
    }

    /**
     * Hide fragment of stack
     */
    @KoinExperimentalAPI
    inline fun <reified F : Fragment> hide() {
        val fragment = fragmentManager.getFragment<F>()
        transactionManager.hide(fragment)
    }

    /**
     * Show fragment of stack
     */
    @KoinExperimentalAPI
    inline fun <reified F : Fragment> detach() {
        val fragment = fragmentManager.getFragment<F>()
        transactionManager.detach(fragment)
    }

    fun setCustomAnimations(
        @AnimatorRes @AnimRes enter: Int,
        @AnimatorRes @AnimRes exit: Int
    ) {
        transactionManager.setCustomAnimations(enter, exit)
    }

    fun setCustomAnimations(
        @AnimatorRes @AnimRes enter: Int,
        @AnimatorRes @AnimRes exit: Int,
        @AnimatorRes @AnimRes popEnter: Int,
        @AnimatorRes @AnimRes popExit: Int
    ) {
        transactionManager.setCustomAnimations(enter, exit, popEnter, popExit)
    }
}