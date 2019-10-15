package com.didapteam.project.education;

import java.util.ArrayList;

public class PlaceData {
    public static String[][] data = new String[][]{
            {"Kabupaten Aceh Singkil", "", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/75/Lambang_Kabupaten_Aceh_Singkil.png/150px-Lambang_Kabupaten_Aceh_Singkil.png"},
            {"Kabupaten Ende", "", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Lambang_Kabupaten_Ende.png/150px-Lambang_Kabupaten_Ende.png"},
            {"Kabupaten Talaud", "", "https://upload.wikimedia.org/wikipedia/id/thumb/f/f1/Logo_Kabupaten_Kepulauan_Talaud.jpg/150px-Logo_Kabupaten_Kepulauan_Talaud.jpg"},
            {"Kabupaten Manokwari", "", "https://upload.wikimedia.org/wikipedia/id/thumb/2/2b/Lambang_Kabupaten_Manokwari.jpg/100px-Lambang_Kabupaten_Manokwari.jpg"},
            {"Kabupaten Malinau", "", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/Lambang_Kabupaten_Malinau.jpeg/125px-Lambang_Kabupaten_Malinau.jpeg"},

    };

    public static ArrayList<Place> getListData(){
        ArrayList<Place> list = new ArrayList<>();
        for(String[] aData : data){
            Place place = new Place();
            place.setName(aData[0]);
            place.setFrom(aData[1]);
            place.setPhoto(aData[2]);

            list.add(place);
        }
        return list;
    }
}
