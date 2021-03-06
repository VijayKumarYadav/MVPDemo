/*
 * Copyright (C) VijayK
 */

package com.example.ui.result;

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
 * Unit tests for the implementation of {@link ResultPresenter}.
 */
public class ResultPresenterTest extends RobolectricTest {

    private ResultPresenter mResultPresenter;

    // mock the require view
    @Mock
    private ResultContract mResultView;

    // mock the data manager.
    @Mock
    private DataManager mDataManager;

    @Before
    public void setup() {
        super.setup();
        MockitoAnnotations.initMocks(this);
        // The presenter wont't update the view unless it's active.
        when(mResultView.isActive()).thenReturn(true);
    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        // Get a reference to the class under test
        mResultPresenter = new ResultPresenter(mDataManager);

        // Then the presenter is set to the view
        mResultPresenter.onAttach(getActivityContext(), mResultView);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                // do nothing
                return null;
            }
        }).when(mResultView).addToActivity(any(Context.class));

        verify(mResultView).onPresenterAttached(mResultPresenter);
    }

    @Test
    public void testLoadResultImageBehaviour() {
        //when presenter is ask to load category images.
        mResultPresenter = new ResultPresenter(mDataManager);
        // Then the presenter is set to the view
        mResultPresenter.onAttach(getActivityContext(), mResultView);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                // do nothing , ignore this call
                return null;
            }
        }).when(mResultView).addToActivity(any(Context.class));

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

        mResultPresenter.loadImages();
        // presenter load the category and call view to show it.
        verify(mResultView).populateImages(any(List.class));
    }
}
