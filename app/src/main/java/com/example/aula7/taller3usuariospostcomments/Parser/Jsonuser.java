package com.example.aula7.taller3usuariospostcomments.Parser;



import com.example.aula7.taller3usuariospostcomments.Model.Usermodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 28/09/2017.
 */

public class Jsonuser {

    public static List<Usermodel> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<Usermodel> usermodelList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject item = jsonArray.getJSONObject(i);
            JSONObject item2 =item.getJSONObject("address");
            JSONObject item3 =item.getJSONObject("company");

            Usermodel usermodel = new Usermodel();
            usermodel.setId(item.getInt("id"));
            usermodel.setName(item.getString("name"));
            usermodel.setUsername(item.getString("username"));
            usermodel.setAddress(item2.getString("city"));
            usermodel.setCompany(item3.getString("name"));
            usermodelList.add(usermodel);
        }
        return usermodelList;
    }

}
