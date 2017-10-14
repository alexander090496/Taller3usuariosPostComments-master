package com.example.aula7.taller3usuariospostcomments.Parser;

import com.example.aula7.taller3usuariospostcomments.Model.Commentsmodel;
import com.example.aula7.taller3usuariospostcomments.Model.Postmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 14/10/2017.
 */

public class Jsonpost {


    public static List<Postmodel> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<Postmodel> postmodelList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject item = jsonArray.getJSONObject(i);

            Postmodel postmodel = new Postmodel();
            postmodel.setId(item.getInt("id"));
            postmodel.setUserId(item.getInt("userId"));
            postmodel.setTitle(item.getString("title"));
            postmodel.setBody(item.getString("body"));


            postmodelList.add(postmodel);
        }
        return postmodelList;
    }
}
