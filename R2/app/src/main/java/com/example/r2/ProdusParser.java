package com.example.r2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProdusParser {

    private final static String ID="id";
    private final static String DENUMIRE="denumire";
    private final static String PRET= "pret";

    //citireProdus

    private static  Produs citireProdus(JSONObject object) throws JSONException {
        long id = object.getLong(ID);
        String denumire = object.getString(DENUMIRE);
        float pret = (float) object.getDouble(PRET);

        return new Produs(id,denumire,pret);
    }

    private static List<Produs> citireProduse(JSONArray array) throws JSONException {
        List<Produs>listaProduse = new ArrayList<>();
        for(int i=0;i<array.length();i++)
        {
            listaProduse.add(citireProdus(array.getJSONObject(i)));
        }

        return listaProduse;
    }

    public static List<Produs> fromJson(String json)//o fac publica ca am nevoie in main
    {
        try {
            JSONArray array = new JSONArray(json);
            return citireProduse(array);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
