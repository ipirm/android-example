package az.azerconnect.bakcell.ui

import az.azerconnect.bakcell.utils.SingleLiveEvent

object EventBus {
    val navigateToSplash = SingleLiveEvent<Boolean>()
}