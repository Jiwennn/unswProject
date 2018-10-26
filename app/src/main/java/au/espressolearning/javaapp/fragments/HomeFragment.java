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
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import au.espressolearning.javaapp.activities.MainActivity;

import au.espressolearning.javaapp.R;

public class HomeFragment extends Fragment {
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    private String username;
    private static final String Tag = "Test";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle(getResources().getString(R.string.title_home));

        Log.d(Tag,"username is " + getArguments());
        if(getArguments() != null){
            username = getArguments().getString("namefromMain");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        TextView userName = (TextView)rootView.findViewById(R.id.subtitle_text);
        /*
        String currentUser = getActivity().getIntent().getStringExtra("name");
        */
        userName.setText(username);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getView() != null) {
            ViewGroup parent = (ViewGroup) getView().getParent();
            parent.removeAllViews();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
