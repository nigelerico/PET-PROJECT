package com.skripsi.nigel.esvira.Model;

public class Userdata {
    /**
     * id : 2
     * name : adinata
     * email : adinata@gmail.com
     * nomor_telepon : 0851112222
     * foto : null
     * tipe : user
     * created_at : 2018-06-02 12:49:31
     * updated_at : 2018-06-02 12:49:31
     */

    private int id;
    private String name;
    private String email;
    private String nomor_telepon;
    private String foto;
    private String tipe;
    private String created_at;
    private String updated_at;

    public Userdata(int id ,String foto,String name, String email, String nomor_telepon){
        this.id = id;
        this.foto = foto;
        this.name = name;
        this.email = email;
        this.nomor_telepon = nomor_telepon;
    }
    public Userdata(int id ,String name, String email, String nomor_telepon){
        this.id = id;
        this.name = name;
        this.email = email;
        this.nomor_telepon = nomor_telepon;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomor_telepon() {
        return nomor_telepon;
    }

    public void setNomor_telepon(String nomor_telepon) {
        this.nomor_telepon = nomor_telepon;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String  foto) {
        this.foto = foto;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
//    private String txt_username;
//    private String txt_email;
//    private String txt_notelp;
//    private int gambaruser;
//
//    public Userdata(String txt_username, String txt_email,String txt_notelp, Integer gambar_user){
//        this.txt_username= txt_username;
//        this.txt_email= txt_email;
//        this.txt_notelp= txt_notelp;
//        this.gambaruser= gambar_user;
//
//    }
//
//
//    public String getTxt_username() { return txt_username; }
//
//    public String getTxt_email() {
//        return txt_email;
//    }
//
//    public String getTxt_notelp() {
//        return txt_notelp;
//    }
//
//    public int getGambaruser() { return gambaruser; }
//
//
//    public void setTxt_username(String txt_username) {
//        this.txt_username = txt_username;
//    }
//
//    public void setTxt_email(String txt_email) {
//        this.txt_email = txt_email;
//    }
//
//    public void setTxt_notelp(String txt_notelp) {
//        this.txt_notelp = txt_notelp;
//    }
//
//    public void setGambaruser(int gambar_user) {
//        this.gambaruser = gambar_user;
//    }


}
