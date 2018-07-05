package com.wpr.newsapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wpr.newsapplication.activities.MainActivity;
import com.wpr.newsapplication.network.InternetConnected;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> testrRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.wpr.newsapplication", appContext.getPackageName());
    }

    //Test to check for recycler view
    @Test
    public void testForRecyclerView() {
        MainActivity mainActivity = testrRule.getActivity();
        View viewById = mainActivity.findViewById(R.id.facts_list);
        assertThat(viewById, notNullValue());
        assertThat(viewById, instanceOf(RecyclerView.class));
    }


    //Test to check internet connectivity
    @Test
    public void testForInternetConnectivity() throws ExecutionException, InterruptedException {
        MainActivity mainActivity = testrRule.getActivity();
        boolean isConnected = new InternetConnected(mainActivity).execute().get();
        assertEquals(true, isConnected);
    }

    //Test to check scroll for recycler view
    @Test
    public void checkScrollForRecyclerView() {
        onView(withId(R.id.facts_list)).perform(RecyclerViewActions.scrollToPosition(4));
    }

}
