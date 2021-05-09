package resources;
//enum is a special class which has collection of methods and constents
public enum APIResources {

    AddPlaceApi("/maps/api/place/add/json"),
    getPlaceApi("/maps/api/place/get/json"),
    deletePlaceApi("/maps/api/place/delete/json");
    private String resource;

    APIResources(String resource) {
        this.resource= resource;
    }

    public String getResource(){
        return resource;
    }
}

