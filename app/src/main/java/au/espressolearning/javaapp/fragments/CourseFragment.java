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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import au.espressolearning.javaapp.Course;
import au.espressolearning.javaapp.R;
import au.espressolearning.javaapp.activities.MainActivity;
import au.espressolearning.javaapp.adapters.CourseAdapter;

public class CourseFragment extends Fragment {

    private RecyclerView rv;
    private static String[] topics={"Topic 1","Topic 2","Topic 3","Topic 4","Topic 5","Topic 6"};
    private static Course[] courses = {
            new Course("Topic 1 : Overview", 1,"Java programming language was originally developed by Sun Microsystems which was initiated by James Gosling and released in 1995 as core component of Sun Microsystems' Java platform (Java 1.0 [J2SE]).", "2Xa3Y4xz8_s"),
            new Course("Topic 2 : Object & Classes", 2, "Software objects also have a state and a behavior. A software object's state is stored in fields and behavior is shown via methods. \nSo in software development, methods operate on the internal state of an object and the object-to-object communication is done via methods.\n", "yy3yLGkuXPk"),
            new Course("Topic 3 : Loop Control", 3,"Java programming language was originally developed by Sun Microsystems which was initiated by James Gosling and released in 1995 as core component of Sun Microsystems' Java platform (Java 1.0 [J2SE]).","eByBsjUazII"),
            new Course("Topic 4 : Strings", 4, "Software objects also have a state and a behavior. A software object's state is stored in fields and behavior is shown via methods. \nSo in software development, methods operate on the internal state of an object and the object-to-object communication is done via methods.\n","to9DPVsdByE"),
            new Course("Topic 5 : Arrays", 5,"Java programming language was originally developed by Sun Microsystems which was initiated by James Gosling and released in 1995 as core component of Sun Microsystems' Java platform (Java 1.0 [J2SE]).","L06uGnF4IpY"),
            new Course("Topic 6 : Inner Classes", 6, "Software objects also have a state and a behavior. A software object's state is stored in fields and behavior is shown via methods. \nSo in software development, methods operate on the internal state of an object and the object-to-object communication is done via methods.\n","b79bkdrrK3M")
    };

    public static CourseFragment newInstance() {
        CourseFragment fragment = new CourseFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_course,null);

        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle(getResources().getString(R.string.title_bar_course));

        //REFERENCE
        rv= (RecyclerView) rootView.findViewById(R.id.courseRV);

        //LAYOUT MANAGER
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        //ADAPTER
        rv.setAdapter(new CourseAdapter(getActivity(),courses));

        return rootView;
    }


    @Override
    public String toString() {
        return "Courses";
    }

}
