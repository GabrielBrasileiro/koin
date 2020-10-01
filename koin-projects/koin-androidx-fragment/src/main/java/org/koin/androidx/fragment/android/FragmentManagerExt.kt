package org.koin.androidx.fragment.android

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import org.koin.androidx.fragment.definition.FragmentTransactionDefinition
import org.koin.androidx.fragment.error.FragmentNotFoundException
import org.koin.core.KoinExperimentalAPI

/**
 * Transaction definition
 */
typealias FragmentManagerDefinition = FragmentTransactionDefinition.() -> Unit

/**
 * Begin transaction to manipulate fragment stack with reflection
 */
@KoinExperimentalAPI
inline fun FragmentManager.beginTransaction(
    definition: FragmentManagerDefinition
): FragmentTransaction {
    val transaction = beginTransaction()
    FragmentTransactionDefinition(this, transaction).apply(definition)
    return transaction
}

/**
 * Recover fragment filtering by your simple name
 */
@KoinExperimentalAPI
inline fun <reified F : Fragment> FragmentManager.getFragment(): Fragment {
    val fragment = fragments.firstOrNull { it::class.java.simpleName == F::class.java.simpleName }
    val message = "${F::class.simpleName} not found in stack of fragments"

    return fragment ?: throw FragmentNotFoundException(message)
}
