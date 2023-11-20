package com.example.bbkiel.ui.project;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;


import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import android.view.View;
import androidx.annotation.NonNull;
import com.example.bbkiel.R;
import com.example.bbkiel.model.Project;
import com.example.bbkiel.model.SubProject;

import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DistrictAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> districtList = new ArrayList<>();
    private HashMap<String,List<SubProject>> subProjectsInAllDistricts;


    public DistrictAdapter(Context context, List<String> districtList, HashMap<String, List<SubProject>> subProjectsInAllDistricts){
        this.context = context;
        this.districtList = districtList;
        this.subProjectsInAllDistricts = subProjectsInAllDistricts;
    }


    @Override
    public SubProject getChild(int districtPosition, int projectPosition) {
        return this.subProjectsInAllDistricts.get(this.districtList.get(districtPosition)).get(projectPosition);
    }

    @Override
    public long getChildId(int districtPosition, int projectPosition) {
        return projectPosition;
    }

    @Override
    public View getChildView(int districtPosition, final int projectPosition,
                             boolean isLastChild, View childView, ViewGroup parent) {

        final String title = getChild(districtPosition, projectPosition).getTitle();
       // final String description = getChild(districtPosition,projectPosition).getDescription();

        if (childView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            childView = layoutInflater.inflate(R.layout.single_district_item, null);
        }

        TextView titleView = childView.findViewById(R.id.expandedDistrictItem);
        titleView.setText(title);

     //   TextView discriptionView = childView.findViewById(R.id.textview_project_information);
     //   discriptionView.setText(description);

        return childView;
    }

    @Override
    public int getChildrenCount(int districtPosition) {
        return this.subProjectsInAllDistricts.get(this.districtList.get(districtPosition)).size();
    }

    @Override
    public String getGroup(int districtPosition) {
        return this.districtList.get(districtPosition);
    }

    @Override
    public int getGroupCount() {
        return this.districtList.size();
    }

    @Override
    public long getGroupId(int districtPosition) {
        return districtPosition;
    }


    @Override
    public View getGroupView(int districtPosition, boolean isExpanded,
                             View groupView, ViewGroup parent) {

        String districtTitle = getGroup(districtPosition);

        if (groupView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            groupView = layoutInflater.inflate(R.layout.single_district_view, null);
        }

        TextView districtTitleView = groupView.findViewById(R.id.districtTitle);
        districtTitleView.setTypeface(null, Typeface.BOLD);
        districtTitleView.setText(districtTitle);
        return groupView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int districtPosition, int projectPosition) {
        return true;
    }
}