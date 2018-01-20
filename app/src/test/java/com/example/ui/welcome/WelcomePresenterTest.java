/*
 * Copyright (C) VijayK
 */

package com.example.ui.welcome;

import android.content.Context;

import com.example.RobolectricTest;
import com.example.data.DataManager;
import com.example.tracking.data.db.tables.Category;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doAnswer;

/**
 * Unit tests for the implementation of {@link WelcomePresenter}.
 */
public class WelcomePresenterTest extends RobolectricTest {

    private WelcomePresenter mWelcomePresenter;

    // mock the require view
    @Mock
    private WelcomeContract mWelcomeView;

    // mock the data manager.
    @Mock
    private DataManager mDataManager;

    @Before
    public void setup() {
        super.setup();
        MockitoAnnotations.initMocks(this);
        // The presenter wont't update the view unless it's active.
        when(mWelcomeView.isActive()).thenReturn(true);
    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        // Get a reference to the class under test
        mWelcomePresenter = new WelcomePresenter(mDataManager);

        // Then the presenter is set to the view
        mWelcomePresenter.onAttach(getActivityContext(), mWelcomeView);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                // do nothing
                return null;
            }
        }).when(mWelcomeView).addToActivity(any(Context.class));

        verify(mWelcomeView).onPresenterAttached(mWelcomePresenter);
    }

    @Test
    public void testLoadWelcomeImageBehaviour() {
        //when presenter is ask to load category images.
        mWelcomePresenter = new WelcomePresenter(mDataManager);
        // Then the presenter is set to the view
        mWelcomePresenter.onAttach(getActivityContext(), mWelcomeView);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                // do nothing , ignore this call
                return null;
            }
        }).when(mWelcomeView).addToActivity(any(Context.class));

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                    DataManager.CallBack callBack = (DataManager.CallBack) arguments[0];
                    List<Category> categories = new ArrayList<>();
                    callBack.onComplete(categories, "");
                }
                return null;
            }
        }).when(mDataManager).getCategories(any(DataManager.CallBack.class));

        mWelcomePresenter.getMostViewImageUrl();
        // presenter load the category and call view to show it.
        verify(mWelcomeView).loadImage(any(String.class));
    }
}
