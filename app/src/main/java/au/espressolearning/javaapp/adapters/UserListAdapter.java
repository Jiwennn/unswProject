package au.espressolearning.javaapp.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import au.espressolearning.javaapp.R;
import au.espressolearning.javaapp.model.User;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView userItemView;

        private UserViewHolder(View itemView) {
            super(itemView);
            userItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<User> mUsers; // Cached copy of words

    UserListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        if (mUsers != null) {
            User current = mUsers.get(position);
            holder.userItemView.setText(current.getUsername());
        } else {
            // Covers the case of data not being ready yet.
            holder.userItemView.setText("No Word");
        }
    }

    void setUsers(List<User> users){
        mUsers = users;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mUsers != null)
            return mUsers.size();
        else return 0;
    }
}