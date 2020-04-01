package me.dcoder.scoreboard.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.dcoder.scoreboard.R;

public class ListFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView.setAdapter(new RecyclerViewAdapter(mListener));
        return recyclerView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Fragment fragment);
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        private final ListItem[] items = new ListItem[]{
                new ListItem(R.string.score, getString(R.string.score)),
                new ListItem(R.string.settings, getString(R.string.settings))
        };
        private final OnListFragmentInteractionListener mListener;

        public RecyclerViewAdapter(ListFragment.OnListFragmentInteractionListener listener) {
            mListener = listener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.item = items[position];
            holder.txtTitle.setText(items[position].title);

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        Fragment fragment;
                        switch (holder.item.id){
                            case R.string.score:
                                fragment = new ScoreFragment();
                                break;
                            case R.string.settings:
                            default:
                                fragment = new SettingsFragment();
                        }
                        mListener.onListFragmentInteraction(fragment);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return items.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View view;
            public final TextView txtTitle;
            public ListItem item;

            public ViewHolder(View view) {
                super(view);
                this.view = view;
                txtTitle = view.findViewById(R.id.list_item_title);
            }
        }

        public class ListItem {
            int id;
            String title;

            public ListItem(int id, String title) {
                this.id = id;
                this.title = title;
            }
        }
    }
}