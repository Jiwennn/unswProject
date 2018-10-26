/*
 * Copyright (c) 2017. Truiton (http://www.truiton.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Mohit Gupt (https://github.com/mohitgupt)
 *
 */

package au.espressolearning.javaapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import au.espressolearning.javaapp.R;
import au.espressolearning.javaapp.activities.MainActivity;

public class ProfileFragment extends Fragment {

    private RecyclerView rv;
    private static String[] spacecrafts={"Test","Test 15","Test 17"};

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle(getResources().getString(R.string.title_bar_profile));
        return inflater.inflate(R.layout.fragment_profile,container,false);

    }


    @Override
    public String toString() {
        return "InterPlanetary";
    }

}
