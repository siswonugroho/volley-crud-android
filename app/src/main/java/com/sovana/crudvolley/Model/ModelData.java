package com.sovana.crudvolley.Model;
public class ModelData {String id, username, grup, nama, password, id_grup;

    public ModelData(){}

    public ModelData(String id, String username, String grup, String nama, String password, String id_grup) {
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.grup = grup;
        this.id_grup = id_grup;

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

    public String getIdGrup() { return id_grup; }
    public void setIdGrup(String id_grup) { this.id_grup = id_grup;}
}