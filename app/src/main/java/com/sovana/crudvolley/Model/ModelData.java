package com.sovana.crudvolley.Model;
public class ModelData {String id, username, grup, nama, password;

    public ModelData(){}

    public ModelData(String id, String username, String grup, String nama, String password) {
        this.id = id;
        this.username = username;
        this.grup = grup;
        this.nama = nama;
        this.password = password;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id;}

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password;}

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama;}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username;}

    public String getGrup() { return grup; }
    public void setGrup(String grup) { this.grup = grup;}
}