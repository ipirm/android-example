package az.azerconnect.bakcell

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import az.azerconnect.data.persistence.SessionManager
import az.azerconnect.bakcell.ui.main.MainActivity
import java.lang.Thread.sleep


@RunWith(AndroidJUnit4ClassRunner::class)
class BottomNavViewTest : BaseRobot() {

    @get:Rule
    val rule = activityScenarioRule<MainActivity>()

    @Test
    fun start() {
        sleep(600)
        register()

//        fillForm()

//        sleep(55000)
    }

    private fun register() {
        SessionManager.loggedIn = false

        val name = "Semid Babayev"
        val username = "babayevsemid"

        doOnView(withId(R.id.orderBtn), click())
//        doOnView(withId(R.id.usernameEdt), typeText(username))

//        if (onView(withId(R.id.positiveBtn)).isDisplayed()) {
//            doOnView(withId(R.id.positiveBtn), click())
//
//            sleep(300)
//            pressBack()
            sleep(5000)

//            login()
//        }
    }

    private fun login() {
        val username = "babayevsemid"

//        doOnView(withId(R.id.usernameEdt), typeText(username))
//        doOnView(withId(R.id.loginBtn), click())
    }


    private fun fillForm() {

        sleep(500)

        val form = when {
//            onView(withText(Form1().form().name)).isDisplayed() -> Form1()
//            onView(withText(Form2().form().name)).isDisplayed() -> Form2()
            else -> null
        }

//        if (form is Form1) {
//            doOnView(withId(R.id.np), swipeLeft())
//            scrollTo(2)
//            checkVariant(form.question2Variant2(0).name)
//            doOnView(withId(R.id.noteEdt), typeText("IOS not bad"))
//        }

        form?.let {
            doOnView(withId(R.id.recyclerView), swipeUp())
//            doOnView(allOf(withId(R.id.saveBtn), isDisplayed()), click())
        }


        doOnView(
            withId(R.id.recyclerView),
            actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
    }

    private fun checkVariant(text: String?) {
        doOnView(allOf(withText(text), isDisplayed()), click())
        sleep(400)
    }

    private fun scrollTo(position: Int) {
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, scrollTo()))
    }
}