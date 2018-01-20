/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.tracking;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.tracking.data.db.CategoryDatabase;
import com.example.tracking.data.db.tables.Category;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
public class CategoryDaoTest {

    private static final Category CATEGORY = new Category("title", "url", 1, "dateTaken");

    private CategoryDatabase mDatabase;

    @Before
    public void initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                CategoryDatabase.class).build();
    }

    @After
    public void closeDb() {
        mDatabase.close();
    }

    // test to insert a category object and try to get it. both should equals
    @Test
    public void insertCategoryAndGet() {
        mDatabase.categoryDao().insertCategory(CATEGORY);
        Category loaded = mDatabase.categoryDao().getCategories().get(0);
        assertCategory(loaded, "title", "url", 1, "dateTaken");
    }

    @Test
    public void insertCategoryReplacesOnConflict() {
        mDatabase.categoryDao().insertCategory(CATEGORY);
        Category newCategory = new Category("title2", "url", 1, "dateTaken2");
        mDatabase.categoryDao().insertCategory(newCategory);
        Category loaded = mDatabase.categoryDao().getCategories().get(0);
        assertCategory(loaded, "title2", "url", 1, "dateTaken2");
    }


    @Test
    public void deleteCategory() {
        mDatabase.categoryDao().insertCategory(CATEGORY);
        mDatabase.categoryDao().deleteCategories();
        List<Category> categories = mDatabase.categoryDao().getCategories();
        assertThat(categories.size(), is(0));
    }

    // tracking of clicking on images. should be saved properly.
    @Test
    public void testCategoryClicked() {
        mDatabase.categoryDao().insertCategory(CATEGORY);
        List<Category> categories = mDatabase.categoryDao().getClickedCategory();
        assertThat(categories.size(), is(0));
        Category newCategory = new Category("title2", "url1", 2, "dateTaken2");
        mDatabase.categoryDao().insertCategory(newCategory);
        categories = mDatabase.categoryDao().getClickedCategory();
        assertThat(categories.size(), is(1));
    }

    // see if you can find most view clicked url safely.
    @Test
    public void testMostViewCategory() {
        mDatabase.categoryDao().insertCategory(CATEGORY);
        Category newCategory = new Category("title2", "url1", 1, "dateTaken2");
        mDatabase.categoryDao().insertCategory(newCategory);
        mDatabase.categoryDao().updateClick("url", 1);
        assertThat(mDatabase.categoryDao().getMostViewedCategoryURL(), is("url"));
    }

    private void assertCategory(Category task, String title, String url, int numberOfClick, String dateTaken) {
        assertThat(task, notNullValue());
        assertThat(task.getTitle(), is(title));
        assertThat(task.getUrl(), is(url));
        assertThat(task.getNumberOfClick(), is(numberOfClick));
        assertThat(task.getDateTaken(), is(dateTaken));
    }
}
