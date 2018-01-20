/*
 * Copyright (C) VijayK
 */
package com.example.ui.itemdetail;

import android.content.Context;

import com.example.RobolectricTest;
import com.example.data.DataManager;
import com.example.ui.category.CategoryContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doAnswer;

/**
 * Unit tests for the implementation of {@link ItemDetailPresenter}.
 */
public class ItemDetailPresenterTest extends RobolectricTest {

    private ItemDetailPresenter mItemDetailPresenter;

    // mock the require view
    @Mock
    private CategoryContract mItemDetailView;

    // mock the data manager.
    @Mock
    private DataManager mDataManager;

    @Before
    public void setup() {
        super.setup();
        MockitoAnnotations.initMocks(this);
        // The presenter wont't update the view unless it's active.
        when(mItemDetailView.isActive()).thenReturn(true);
    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        // Get a reference to the class under test
        mItemDetailPresenter = new ItemDetailPresenter(mDataManager);

        // Then the presenter is set to the view
        mItemDetailPresenter.onAttach(getActivityContext(), mItemDetailView);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                // do nothing
                return null;
            }
        }).when(mItemDetailView).addToActivity(any(Context.class));

        verify(mItemDetailView).onPresenterAttached(mItemDetailPresenter);
    }
}

