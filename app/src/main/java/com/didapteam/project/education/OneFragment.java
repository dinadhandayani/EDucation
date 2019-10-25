package com.didapteam.project.education;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class OneFragment extends Fragment{

    private RecyclerView rvCategory;
    private ArrayList<Place> list = new ArrayList<>();

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.activity_verification_result, container, false);

        /*rvCategory = rootView.findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list.addAll(PlaceData.getListData());
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        PlaceListAdapter listPlaceAdapter =  new PlaceListAdapter(list);
        rvCategory.setAdapter(listPlaceAdapter);

        listPlaceAdapter.setOnItemClickCallback(new PlaceListAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Place data) {
                showSelectedPlace(data);
            }
        }); */

        return rootView;
    }

    private void showSelectedPlace(Place place){
        Toast.makeText(getActivity(), "Kamu memilih " + place.getName(), Toast.LENGTH_SHORT).show();
    }

}