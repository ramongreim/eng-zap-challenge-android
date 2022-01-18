package br.ramongreim.code_challenge_zap

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ZapGroupInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("br.ramongreim.code_challenge_zap", appContext.packageName)
    }

    @Test
    fun testarVivaReal() {
        onView(withId(R.id.btVivaReal)).perform(click())
        Thread.sleep(2000)
        hasComponent("br.ramongreim.code_challenge_zap.imovel.ImoveisActivity")
    }

    @Test
    fun testarZap() {
        onView(withId(R.id.btZap)).perform(click())
        Thread.sleep(2000)
        hasComponent("br.ramongreim.code_challenge_zap.imovel.ImoveisActivity")

    }

    @Test
    fun testarTelaImovelViaZap() {
        onView(withId(R.id.btZap)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.rvImoveis)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        hasComponent("br.ramongreim.code_challenge_zap.imovel.ImoveisActivity")

    }

    fun testarTelaImovelViaVivaReal() {
        onView(withId(R.id.btVivaReal)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.rvImoveis)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        hasComponent("br.ramongreim.code_challenge_zap.imovel.ImoveisActivity")


    }
}