/**
 * Copyright (C) VijayK
 */
package com.example.tracking;

import com.example.RobolectricTest;
import com.example.tracking.data.TrackingImpl;
import com.example.tracking.data.db.CategoryDao;
import com.example.tracking.data.db.tables.Category;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.doAnswer;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Robolectric test for tracking modules.
 */
public class TrackingImplTest extends RobolectricTest {

    private CategoryDao mCategoryDao;
    private TrackingImpl mTracking;
    private static final Category CATEGORY = new Category("title", "url", 1, "dateTaken");

    @Before
    public void setUp() {
        mCategoryDao = mock(CategoryDao.class);
        mTracking = TrackingImpl.getInstance(mCategoryDao);
    }

    @After
    public void tearDown() {
        mTracking.disposeInstance();
    }

    // test to track first time user.
    @Test
    public void testIsFirstTimeUser() {
        when(mCategoryDao.getClickedCategory()).thenReturn(new ArrayList<>());
        assertThat(mTracking.getClickedCategory()).isEmpty();
    }

    // test to track most viewed url
    @Test
    public void testMostViewUrl() {
        when(mCategoryDao.getMostViewedCategoryURL()).thenReturn("url");
        assertThat(mTracking.getMostViewUrl()).isEqualTo("url");
    }

    @Test
    public void testGetCategory() {
        when(mCategoryDao.getCategories()).thenReturn(Collections.singletonList(CATEGORY));
        assertThat(mTracking.getCategories().get(0)).isEqualTo(CATEGORY);
    }

    @Test
    public void testGetClickedCategory() {
        when(mCategoryDao.getClickedCategory()).thenReturn(Collections.singletonList(CATEGORY));
        assertThat(mTracking.getClickedCategory().get(0)).isEqualTo(CATEGORY);
    }

    // test to track click on category images.
    @Test
    public void testTrackClick() {
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                    int increment = (int) arguments[1];
                    assertThat(increment).isEqualTo(1);
                }
                return null;
            }
        }).when(mCategoryDao).updateClick(any(String.class), any(Integer.class));
        mTracking.trackClick("url");
    }
}
