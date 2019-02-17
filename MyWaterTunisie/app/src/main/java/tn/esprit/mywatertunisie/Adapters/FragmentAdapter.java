package tn.esprit.mywatertunisie.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tn.esprit.mywatertunisie.Fragments.Eau_Fragment;
import tn.esprit.mywatertunisie.Fragments.Electricite_Fragment;
import tn.esprit.mywatertunisie.Fragments.Piscine_Fragment;


public class FragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public FragmentAdapter (Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
            switch (position) {
                case 0:

                    return new Eau_Fragment();
                case 1:
                    return new Piscine_Fragment();
                case 2:
                    return new Electricite_Fragment();

                default:
                    return null;
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:

                return "Traitement des Eaux";
            case 1:
                return "Piscines";
            case 2:
                return "Electricité Batiment";

            default:
                return null;
        }
    }

}