/*
 * Copyright (C) VijayK
 */

package com.example.ui.category;

import com.example.tracking.data.db.tables.Category;
import com.example.ui.base.BaseView;

import java.util.List;

/**
 * Interface for view to be used by presenter for communication.
 */
public interface CategoryContract extends BaseView {

    /**
     * Loaded category list.
     */
    void populateCategory(List<Category> data);

    interface  test {
           class A {

          }
    }
}
