package com.project.resource.country;


import com.project.sli.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date 2019/06/02
 */
public class Countries {
    private String countryName;
    private String countryNameSimple;
    private int countryImageId;
    private  List<Countries> countries = new ArrayList<>();

    public Countries (){
        initCountryList();
    }
    public Countries(String countryName,int countryImageId ,String countryNameSimple){
        this.countryName = countryName;
        this.countryImageId = countryImageId;
        this.countryNameSimple = countryNameSimple;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getCountryImageId() {
        return countryImageId;
    }

    public String getCountryNameSimple() {
        return countryNameSimple;
    }

    private void initCountryList(){
        countries.add(new Countries("Argentina", R.drawable.ar,"ar"));
        countries.add(new Countries("Australia",R.drawable.au,"au"));
        countries.add(new Countries("Austria",R.drawable.at,"at"));
        countries.add(new Countries("Belgium",R.drawable.be,"be"));
        countries.add(new Countries("Brazil",R.drawable.br,"br"));
        countries.add(new Countries("Bulgaria",R.drawable.bg,"bg"));
        countries.add(new Countries("Canada",R.drawable.ca,"ca"));
        countries.add(new Countries("China",R.drawable.cn,"cn"));
        countries.add(new Countries("Colombia",R.drawable.co,"co"));
        countries.add(new Countries("Cuba",R.drawable.cu,"cu"));
        countries.add(new Countries("Czech",R.drawable.cz,"cz"));
        countries.add(new Countries("Egypt",R.drawable.eg,"eg"));
        countries.add(new Countries("France",R.drawable.fr,"fr"));
        countries.add(new Countries("Germany",R.drawable.de,"de"));
        countries.add(new Countries("Greece",R.drawable.gr,"gr"));
        countries.add(new Countries("HongKong",R.drawable.hk,"kh"));
        countries.add(new Countries("Hungary",R.drawable.hu,"hu"));
        countries.add(new Countries("India",R.drawable.in,"in"));
        countries.add(new Countries("Indonesia",R.drawable.id,"id"));
        countries.add(new Countries("Ireland",R.drawable.ie,"ie"));
        countries.add(new Countries("Israel",R.drawable.il,"il"));
        countries.add(new Countries("Italy",R.drawable.it,"it"));
        countries.add(new Countries("Japan",R.drawable.jp,"jp"));
        countries.add(new Countries("Latvia",R.drawable.au,"au"));
        countries.add(new Countries("Lithuania",R.drawable.lt,"lt"));
        countries.add(new Countries("Malaysia",R.drawable.my,"my"));
        countries.add(new Countries("Mexico",R.drawable.mx,"mx"));
        countries.add(new Countries("Morocco",R.drawable.ma,"ma"));
        countries.add(new Countries("Netherlands",R.drawable.nl,"nl"));
        countries.add(new Countries("NewZealand",R.drawable.nz,"nz"));
        countries.add(new Countries("Nigeria",R.drawable.ng,"ng"));
        countries.add(new Countries("Norway",R.drawable.no,"no"));
        countries.add(new Countries("Philippines",R.drawable.ph,"ph"));
        countries.add(new Countries("Poland",R.drawable.pl,"pl"));
        countries.add(new Countries("Portugal",R.drawable.pt,"pt"));
        countries.add(new Countries("Romania",R.drawable.ro,"ro"));
        countries.add(new Countries("Russia",R.drawable.ru,"ru"));
        countries.add(new Countries("Arabia",R.drawable.sa,"sa"));
        countries.add(new Countries("Serbia",R.drawable.rs,"rs"));
        countries.add(new Countries("Singapore",R.drawable.sg,"sg"));
        countries.add(new Countries("Slovakia",R.drawable.sk,"sk"));
        countries.add(new Countries("Slovenia",R.drawable.si,"si"));
        countries.add(new Countries("SouthAfrica",R.drawable.za,"za"));
        countries.add(new Countries("SouthKorea",R.drawable.kr,"kr"));
        countries.add(new Countries("Sweden",R.drawable.se,"se"));
        countries.add(new Countries("Switzerland",R.drawable.ch,"ch"));
        countries.add(new Countries("Taiwan",R.drawable.tw,"tw"));
        countries.add(new Countries("Thailand",R.drawable.th,"th"));
        countries.add(new Countries("Turkey",R.drawable.tr,"tr"));
        countries.add(new Countries("UAE",R.drawable.ae,"ae"));
        countries.add(new Countries("Ukraine",R.drawable.ua,"ua"));
        countries.add(new Countries("UK",R.drawable.gb,"gb"));
        countries.add(new Countries("US",R.drawable.us,"us"));
        countries.add(new Countries("Venuzuela",R.drawable.ve,"ve"));










































    }

    public List<Countries> getCountries(){
        return countries;
    }
}
