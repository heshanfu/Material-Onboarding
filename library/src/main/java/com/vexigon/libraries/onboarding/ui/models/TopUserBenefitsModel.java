package com.vexigon.libraries.onboarding.ui.models;

/*
 * Copyright 2017 Vexigon, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.vexigon.libraries.onboarding.obj.benefits.Page;
import com.vexigon.libraries.onboarding.ui.activity.UserBenefitsActivity;
import com.vexigon.libraries.onboarding.util.BenefitsKeys;

import java.util.ArrayList;

/**
 * Created by Andrew Quebe on 3/2/2017.
 */

@SuppressWarnings("FieldCanBeLocal")
public class TopUserBenefitsModel {

    private Context context;
    private ArrayList<Page> pages = new ArrayList<>();

    /**
     * Creates a new instance of the TopUserBenefitsModel
     *
     * @param context your activity context
     */
    public TopUserBenefitsModel(@NonNull Activity context) {
        this.context = context;
    }

    /**
     * Sets all three pages for the TopUserBenefitsModel
     *
     * @param page1 the first page in the activity.
     * @param page2 the second page in the activity.
     * @param page3 the third page in the activity.
     * @return TopUserBenefitsModel
     */
    public TopUserBenefitsModel setupSlides(Page page1, Page page2, Page page3) {
        this.pages.add(0, page1);
        this.pages.add(1, page2);
        this.pages.add(2, page3);
        return this;
    }

    /**
     * Checks text and image resources for null values, and returns an intent that stores the non null values.
     *
     * @return Intent
     */
    private Intent buildIntent() {
        String[] titleText = new String[3], subtitleText = new String[3], buttonText = new String[3];
        int[] illustrationRes = new int[3];

        int count = 0;
        for (Page page : pages) {
            if (page.getTitle().equals(""))
                throw new RuntimeException("The title for page " + (count + 1) + " was blank.");
            else if (page.getSubtitle().equals(""))
                throw new RuntimeException("The subtitle for page " + (count + 1) + " was blank.");
            else if (page.getDrawableRes() == 0) {
                throw new RuntimeException("The image resource for page " + (count + 1) + " was blank.");
            } else if (page.getButtonText() == null) {
                titleText[count] = page.getTitle();
                subtitleText[count] = page.getSubtitle();
                buttonText[count] = "Get Started";
                illustrationRes[count] = page.getDrawableRes();
                count++;
            } else {
                titleText[count] = page.getTitle();
                subtitleText[count] = page.getSubtitle();
                buttonText[count] = page.getButtonText();
                illustrationRes[count] = page.getDrawableRes();
                count++;
            }
        }

        return new Intent(context, UserBenefitsActivity.class)
                .putExtra(BenefitsKeys.TITLE_TEXT, titleText)
                .putExtra(BenefitsKeys.SUBTITLE_TEXT, subtitleText)
                .putExtra(BenefitsKeys.BUTTON_TEXT, buttonText)
                .putExtra(BenefitsKeys.ILLUSTRATION_RES, illustrationRes);
    }

    /**
     * Launches the activity with the buildIntent() data stored.
     */
    public void launch() {
        context.startActivity(buildIntent());
    }
}
