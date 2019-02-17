package tn.esprit.mywatertunisie;

public class URLs {

//        private static final String baseURL = "http://172.18.88.182";
//    private static final String baseURL = "http://10.0.2.2";
//        private static final String baseURL = "http://192.168.43.234";
    private static final String baseURL = "http://192.168.1.12";
    private static final String basePORT = ":3003";
    //    public static final String URL_REGISTER = "http://localhost:3003/user_create";
    public static final String URL_REGISTER = baseURL + basePORT + "/user/user_create";
    public static final String URL_LOGIN = baseURL + basePORT + "/user/userss";
    public static final String URL_GET_ALL_USERS = baseURL + basePORT + "/user/userss";
    public static final String URL_GET_USERS_BY_ID = baseURL + basePORT + "/user/userss/id";
    public static final String URL_PROD_EAU = baseURL + basePORT + "/produit/show/eau";
    public static final String URL_PROD_PISCINE = baseURL + basePORT + "/produit/show/piscine";
    public static final String URL_PROD_ELECT = baseURL + basePORT + "/produit/show/elect";
    public static final String URL_PROD_BY_ID = baseURL + basePORT + "/produit/show/";
    public static final String URL_PROD = baseURL + basePORT + "/produit/show";
    public static final String URL_IMAGES_CHANTIERS = baseURL + basePORT + "/chantiers/show/";
    public static final String URL_ELECT_LIST = baseURL + basePORT + "/electricite/show";
    public static final String URL_IMG = baseURL + "/mini_project_imgs/";
    public static final String URL_ADD_TO_CART = baseURL + basePORT + "/carte/create";
    public static final String URL_SHOW_CART_BY_USER = baseURL + basePORT + "/carte/show/";


    //  public static final String URL_PROD_EAU = "http://192.168.1.3:3003/prodeau";

}
