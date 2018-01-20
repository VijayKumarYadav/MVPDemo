/*
 * Copyright (C) VijayK
 */
package com.example.ui.category;

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
 * Unit tests for the implementation of {@link CategoryPresenter}.
 */
public class CategoryPresenterTest extends RobolectricTest {

    private CategoryPresenter mCategoryPresenter;

    // mock the require view
    @Mock
    private CategoryContract mCategoryView;

    // mock the data manager.
    @Mock
    private DataManager mDataManager;

    @Before
    public void setup() {
        super.setup();
        MockitoAnnotations.initMocks(this);
        // The presenter wont't update the view unless it's active.
        when(mCategoryView.isActive()).thenReturn(true);
    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        // Get a reference to the class under test
        mCategoryPresenter = new CategoryPresenter(mDataManager);

        // Then the presenter is set to the view
        mCategoryPresenter.onAttach(getActivityContext(), mCategoryView);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                // do nothing
                return null;
            }
        }).when(mCategoryView).addToActivity(any(Context.class));

        verify(mCategoryView).onPresenterAttached(mCategoryPresenter);
    }

    @Test
    public void testLoadCategoryImageBehaviour() {
        //when presenter is ask to load category images.
        mCategoryPresenter = new CategoryPresenter(mDataManager);
        // Then the presenter is set to the view
        mCategoryPresenter.onAttach(getActivityContext(), mCategoryView);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                // do nothing , ignore this call
                return null;
            }
        }).when(mCategoryView).addToActivity(any(Context.class));

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

        mCategoryPresenter.loadCategoryImages();
        // presenter load the category and call view to show it.
        verify(mCategoryView).populateCategory(any(List.class));
    }
}
