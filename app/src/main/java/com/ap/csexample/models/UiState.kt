package com.ap.csexample.models

/**
 * Set of available UI states. These can be used during async data retrieval tasks to trigger
 * UI changes. For tasks with latency a 'loading' state would be used, however in this scenario it
 * was redundant.
 */
sealed class UiState {

    /**
     * Initialised screen state.
     */
    object InitialState : UiState()

    /**
     * State to be used whilst loading.
     */
    object LoadingState : UiState()

    /**
     * State to be used when action is a success.
     */
    object SuccessState : UiState()

    /**
     * State for when an error occurs.
     */
    object ErrorState : UiState()
}