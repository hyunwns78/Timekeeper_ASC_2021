package org.techtown.tab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ContentsPagerAdapter extends FragmentStateAdapter {

    private int mPageCount = 3;

    public ContentsPagerAdapter(AppCompatActivity fm){
        super(fm);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                fragment_main fragment_main = new fragment_main();
                return fragment_main;
            case 1:
                fragment_activity fragment_activity = new fragment_activity();
            case 2:
                fragment_graph fragment_graph = new fragment_graph();
            default:
                return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mPageCount;
    }
}
