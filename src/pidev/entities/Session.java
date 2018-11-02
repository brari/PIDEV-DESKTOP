/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

/**
 *
 * @author hp
 */
public class Session {
    private static int id;
    private static String first_name;
    private static String last_name;
    private static String email;
    private static String user_roles;



    public static void setSession(int id,String first_name,String last_name,String email,String user_roles) {
        Session.id = id=1;
        Session.first_name = first_name;
        Session.last_name = last_name;
        Session.email = email;
        Session.user_roles = user_roles;
    }
    public static void destroySession(){
        Session.id =0;
        Session.first_name = "";
        Session.last_name = "";
        Session.email = "";
        Session.user_roles = "";
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Session.id = id;
    }

    public static String getFirst_name() {
        return first_name;
    }

    public static void setFirst_name(String first_name) {
        Session.first_name = first_name;
    }

    public static String getLast_name() {
        return last_name;
    }

    public static void setLast_name(String last_name) {
        Session.last_name = last_name;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Session.email = email;
    }

    public static String getUser_roles() {
        return user_roles;
    }

    public static void setUser_roles(String user_roles) {
        Session.user_roles = user_roles;
    }
}
