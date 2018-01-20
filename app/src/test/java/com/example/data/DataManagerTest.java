/*
 * Copyright (C) VijayK
 */
package com.example.data;

import com.example.RobolectricTest;
import com.example.data.network.NetworkImpl;
import com.example.tracking.data.TrackingImpl;
import com.example.tracking.data.db.tables.Category;

import org.assertj.core.api.Java6Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doAnswer;

/**
 * Test for {@link DataManager}. DataManager is responsible for managing all data either from database or from network.
 */
public class DataManagerTest extends RobolectricTest {

    private DataManagerImpl mDataManager;
    private TrackingImpl mTracking;
    private NetworkImpl mNetwork;

    @Before
    public void setup() {
        mTracking = mock(TrackingImpl.class);   // mock tracking to avoid dependency
        mNetwork = mock(NetworkImpl.class);  // mock network to avoid dependency
        mDataManager = DataManagerImpl.getInstance(mTracking, mNetwork);
    }

    @After
    public void cleanUp() {
        DataManagerImpl.dispose();
    }

    @Test
    public void testPreConditions() {
        assertNotNull(mDataManager);
    }

    @Test
    public void testGetCategoryWhenDataIsSaved() throws InterruptedException {
        Category category = new Category("title", "url", 1, "dateTaken");
        when(mTracking.getCategories()).thenReturn(Collections.singletonList(category));
        mDataManager.getCategories((data, status) -> {
            assertThat(data.get(0), is(category));
        });
    }

    @Test
    public void testGetCategoryWhenDataIsNotSaved() {
        Category category = new Category("title", "url", 1, "dateTaken");
        when(mNetwork.getCategories()).thenReturn(Collections.singletonList(category));
        mDataManager.getCategories((data, status) -> {
            assertThat(data, is(category));
        });
    }

    @Test
    public void testGetImages() {
        Category category = new Category("title", "url", 1, "dateTaken");
        when(mNetwork.getImages()).thenReturn(Collections.singletonList(category));
        mDataManager.getCategories((data, status) -> {
            assertThat(data, is(category));
        });
    }

    @Test
    public void testTrackClick() {
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                    String url = (String) arguments[1];
                    Java6Assertions.assertThat(url).isEqualTo("url");
                }
                return null;
            }
        }).when(mTracking).trackClick(any(String.class));
        mDataManager.trackClick("url");
    }
}
