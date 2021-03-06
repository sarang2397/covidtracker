package com.example.covid19tracker.ui.country;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19tracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CountryFragment extends Fragment
{
    RecyclerView rvCovidCountry;
    ProgressBar progressBar;

    ArrayList<CovidCountry>covidCountries;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_country, container, false);

        rvCovidCountry=root.findViewById(R.id.rvCovidCountry);
        progressBar=root.findViewById(R.id.progress_circular_country);
        rvCovidCountry.setLayoutManager(new LinearLayoutManager(getActivity()));

        getDataFromServer();




        return root;
    }

    private void showRecyclerview()
    {
        CovidCountryAdapter covidCountryAdapter=new CovidCountryAdapter(covidCountries);
        rvCovidCountry.setAdapter(covidCountryAdapter);
    }

    private void getDataFromServer()
    {
        String url="https://corona.lmao.ninja/v2/countries";
        progressBar.setVisibility(View.GONE);
        covidCountries=new ArrayList<>();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject data = jsonArray.getJSONObject(i);
                        covidCountries.add(new CovidCountry(data.getString("country"), data.getString("cases")));

                    }
                    showRecyclerview();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);

            }

    });
         Volley.newRequestQueue(getActivity()).add(stringRequest);
    }
}
