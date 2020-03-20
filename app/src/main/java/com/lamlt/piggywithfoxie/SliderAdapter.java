package com.lamlt.piggywithfoxie;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SliderAdapter extends FragmentStatePagerAdapter {

    public SliderAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        //add fragment here
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new DetailsFragment();
            case 2:
                return new LetterFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        //count of yor screen
        return 3;
    }
}
