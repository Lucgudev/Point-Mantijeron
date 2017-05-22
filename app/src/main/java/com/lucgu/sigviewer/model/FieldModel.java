package com.lucgu.sigviewer.model;

/**
 * Created by 1414 on 5/22/2017.
 */

public class FieldModel
{
    private double Latitude;
    private double longitude;
    private String id;
    private String nama;
    private String alamat;
    private String kontak;
    private String tipe;
    private String desa;
    private String kecamatan;
    private String kabupaten;
    private String prov;
    private String master;

    public double getLatitude()
    {
        return Latitude;
    }

    public void setLatitude(double latitude)
    {
        Latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getNama()
    {
        return nama;
    }

    public void setNama(String nama)
    {
        this.nama = nama;
    }

    public String getAlamat()
    {
        return alamat;
    }

    public void setAlamat(String alamat)
    {
        this.alamat = alamat;
    }

    public String getKontak()
    {
        return kontak;
    }

    public void setKontak(String kontak)
    {
        this.kontak = kontak;
    }

    public String getTipe()
    {
        return tipe;
    }

    public void setTipe(String tipe)
    {
        this.tipe = tipe;
    }

    public String getDesa()
    {
        return desa;
    }

    public void setDesa(String desa)
    {
        this.desa = desa;
    }

    public String getKecamatan()
    {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan)
    {
        this.kecamatan = kecamatan;
    }

    public String getKabupaten()
    {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten)
    {
        this.kabupaten = kabupaten;
    }

    public String getProv()
    {
        return prov;
    }

    public void setProv(String prov)
    {
        this.prov = prov;
    }

    public String getMaster()
    {
        return master;
    }

    public void setMaster(String master)
    {
        this.master = master;
    }
}
