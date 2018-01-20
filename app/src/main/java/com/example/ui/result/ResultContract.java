/*
 * Copyright (C) VijayK
 */

package com.example.ui.result;

import com.example.tracking.data.db.tables.Category;
import com.example.ui.base.BaseView;

import java.util.List;

/**
 * Interface for view to be used by presenter for communication.
 */
public interface ResultContract extends BaseView {

    void populateImages(List<Category> data);
}
