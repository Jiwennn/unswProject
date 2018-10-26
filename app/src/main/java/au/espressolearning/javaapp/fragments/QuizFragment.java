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

import java.util.ArrayList;

import au.espressolearning.javaapp.Quiz;
import au.espressolearning.javaapp.R;
import au.espressolearning.javaapp.activities.MainActivity;
import au.espressolearning.javaapp.adapters.QuizAdapter;

public class QuizFragment extends Fragment {

    private RecyclerView rv;

    private static Quiz[][] quiz = {{
            new Quiz("1-1", "1-1.png", "Select Option 1 if the output is Base::show() called? \nSelect Option 2 if the output is Derived::show() called?", false, "Topic 1 : Overview"),
            new Quiz("1-2", "1-2.png", "Select Option 1 if there is a compiler error. \nSelect Option 2 if there is a runtime error", true, "Topic 1 : Overview"),
            new Quiz("1-3", "1-3.png", "Select Option 1 if the output is Base::show() called? \nSelect Option 2 if the output is Derived::show() called?", false, "Topic 1 : Overview")
    }, {
            new Quiz("2-1", "2-1.png", "Select Option 1 if there is a runtime error. \nSelect Option 2 if there is a compiler error", false, "Topic 2 : Object & Classes"),
            new Quiz("2-2", "2-2.png", "Select Option 1 if there is a runtime error. \nSelect Option 2 if the output is 0 ", false, "Topic 2 : Object & Classes"),
            new Quiz("2-3", "2-3.png", "Select Option 1 if the output is values of obj1: \na = 11 b = 21\nvalues of obj2: \na = 11 b = 21. \nSelect Option 2 if the output is \na = 11 b = 21\nvalues of obj2: \na = 10 b = 20. ", true, "Topic 2 : Object & Classes")
    }, {
            new Quiz("3-1", "3-1.png", "What will be the output of the following program? \nSelect Option 1 if the output is  No Output. \nSelect Option 2 if there is a compiler error", false, "Topic 3 : Loop Control"),
            new Quiz("3-2", "3-2.png", "What will be the output of the following program? \nSelect Option 1 if the output is HELLO GEEKS. \nSelect Option 2 if the output is a compiler error", false, "Topic 3 : Loop Control"),
            new Quiz("3-3", "3-3.png", "What will be the output of the following program? \nSelect Option 1 if the output is HI. \nSelect Option 2 if the output is HELLO GEEKS", true, "Topic 3 : Loop Control")
    }, {
            new Quiz("4-1", "4-1.png", "What will be the output of the following class? \nSelect Option 1 if the output is hello. \nSelect Option 2 if the output is HELLO", true, "Topic 4 : Strings"),
            new Quiz("4-2", "4-2.png", "Select Option 1 if the output is  false, false. \nSelect Option 2 if the output is false, true", false, "Topic 4 : Strings"),
            new Quiz("4-3", "4-3.png", "What is the difference b/w String and StringBuilder? \nSelect Option 1 if the answer is String is immutable, StringBuilder is not. \nSelect Option 2 if the answer is StringBuilder is thread safe, String is not.", true, "Topic 4 : Strings")
    }, {
            new Quiz("5-1", "5-1.png", "Select Option 1 if the output is 10 20 30 40 50. \nSelect Option 2 if the output is 10 20 30 40", true, "Topic 5 : Arrays"),
            new Quiz("5-2", "5-2.png", "Select Option 1 if the output is 0 0. \nSelect Option 2 if the output is a compiler error", false, "Topic 5 : Arrays"),
            new Quiz("5-3", "5-3.png", "Select Option 1 if the output is 9\n7 8\n4 5 6\n0 1 2 3 . \nSelect Option 2 if the output is 0\n1 2\n3 4 5\n6 7 8 9 ", false, "Topic 5 : Arrays")
    }, {
            new Quiz("6-1", "6-1.png", "Select Option 1 if the output is 20. \nSelect Option 2 if the output is compiler error", false, "Topic 6 : Inner Classes"),
            new Quiz("6-2", "6-2.png", "Select Option 1 if the output is 0 0. \nSelect Option 2 if the output is a compiler error", false, "Topic 6 : Inner Classes"),
            new Quiz("6-2", "6-2.png", "Select Option 1 if the output is 0 0. \nSelect Option 2 if the output is a compiler error", false, "Topic 6 : Inner Classes")
    }};



    public static QuizFragment newInstance() {
        QuizFragment fragment = new QuizFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz, null);

        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle(getResources().getString(R.string.title_bar_quiz));
        //REFERENCE
        rv = (RecyclerView) rootView.findViewById(R.id.quizRV);

        //LAYOUT MANAGER
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        //ADAPTER
        rv.setAdapter(new QuizAdapter(getActivity(), quiz));

        return rootView;
    }


    public static Quiz[][] getQuiz() {
        return quiz;
    }

    @Override
    public String toString() {
        return "Questions";
    }

}
