package resources;

import pojo.AddPlace;

public class TestDataBuild {



    public AddPlace addPlacePayload(String name,String language, String address){

        AddPlace p =new AddPlace();
        p.setAccuracy(50);
   //     p.setAddress("Adding adress");
   //     p.setLanguage("English");
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName(name);
    //      p.setName("Nipun");
        java.util.List<String> myList =new java.util.ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);
        pojo.Location l =new pojo.Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);

        p.setLocation(l);

        return p;
    }

    public String deletePayload(String placeId){
        return "{\r\n\"place_id\":\""+placeId+"\"\r\n}";
    }
}
