package com.hellotamila.agrofarming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> a = new ArrayList<String>();
        a.add("Rice");


        List<String> b = new ArrayList<String>();
        b.add("BlackGram");
        b.add("GreenGram");

        List<String> c = new ArrayList<String>();
        c.add("IrrigatedMaize");
        c.add("RainFedMaize");

        List<String> d = new ArrayList<String>();

        d.add("IrrigatedCotton");
        d.add("RainFedCotton");

        List<String> e = new ArrayList<String>();

        e.add("Sugarcane");

        expandableListDetail.put("Cereals", a);
        expandableListDetail.put("Pulses", b);
        expandableListDetail.put("Millet", c);
        expandableListDetail.put("Cotton", d);
        expandableListDetail.put("Sugarcane", e);

        return expandableListDetail;

    }
}
