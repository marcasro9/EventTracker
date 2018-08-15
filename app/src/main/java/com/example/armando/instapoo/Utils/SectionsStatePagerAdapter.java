package com.example.armando.instapoo.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by marca on 09/09/2017.
 */

public class SectionsStatePagerAdapter extends FragmentStatePagerAdapter{

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final HashMap<Fragment, Integer> mFragments = new HashMap<>();
    private final HashMap<String , Integer> mFragmentsNumbers = new HashMap<>();
    private final HashMap<Integer, String> mFragmentNames= new HashMap<>();


    public SectionsStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment,String fragmentNames){
        mFragmentList.add(fragment);
        mFragments.put(fragment, mFragmentList.size()-1);
        mFragmentsNumbers.put(fragmentNames, mFragmentList.size()-1);
        mFragmentNames.put(mFragmentList.size()-1,fragmentNames);
    }

    /**
     * return the fragments with the name @param
     * @param fragmentName
     * @return
     */
    public  Integer getFragmentNumber(String fragmentName){
        if (mFragmentsNumbers.containsKey(fragmentName)){
            return mFragmentsNumbers.get(fragmentName);
        }else{
            return null;
        }
    }

    /**
     * return the fragments with the name @param
     * @param fragment
     * @return
     */
    public  Integer getFragmentNumber(Fragment fragment){
        if (mFragmentsNumbers.containsKey(fragment)){
            return mFragmentsNumbers.get(fragment);
        }else{
            return null;
        }
    }


    /**
     * return the fragments with the name @param
     * @param fragmentNumber
     * @return
     */
    public  String getFragmentName(Integer fragmentNumber){
        if (mFragmentNames.containsKey(fragmentNumber)){
            return mFragmentNames.get(fragmentNumber);
        }else{
            return null;
        }
    }
}
