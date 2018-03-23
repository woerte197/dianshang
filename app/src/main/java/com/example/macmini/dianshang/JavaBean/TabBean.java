package com.example.macmini.dianshang.JavaBean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.macmini.dianshang.BR;
import com.example.macmini.dianshang.Bottom.BaseFragment;

/**
 * Created by macmini on 2018/3/22.
 */

public class TabBean extends BaseObservable{
    private String title;
    private BaseFragment fragment;
    private boolean isselected;
    private int unselectedimage;
    private int selectedimage;

    public TabBean(String title, BaseFragment fragment, boolean isselected, int unselectedimage, int selectedimage) {
        this.title = title;
        this.fragment = fragment;
        this.isselected = isselected;
        this.unselectedimage = unselectedimage;
        this.selectedimage = selectedimage;
    }
    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }
    @Bindable
    public BaseFragment getFragment() {
        return fragment;
    }

    public void setFragment(BaseFragment fragment) {
        this.fragment = fragment;
        notifyPropertyChanged(BR.fragment);
    }
   @Bindable
    public boolean isIsselected() {
        return isselected;
    }

    public void setIsselected(boolean isselected) {
        this.isselected = isselected;
        notifyPropertyChanged(BR.isselected);
    }
    @Bindable
    public int getUnselectedimage() {
        return unselectedimage;
    }

    public void setUnselectedimage(int unselectedimage) {
        this.unselectedimage = unselectedimage;
        notifyPropertyChanged(BR.unselectedimage);
    }
    @Bindable
    public int getSelectedimage() {
        return selectedimage;
    }

    public void setSelectedimage(int selectedimage) {
        this.selectedimage = selectedimage;
        notifyPropertyChanged(BR.selectedimage);
    }

    @Override
    public String toString() {
        return "TabBean{" +
                "title='" + title + '\'' +
                ", fragment=" + fragment +
                ", isselected=" + isselected +
                ", unselectedimage=" + unselectedimage +
                ", selectedimage=" + selectedimage +
                '}';
    }
}
