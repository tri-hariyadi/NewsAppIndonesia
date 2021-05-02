package com.example.newsapp.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FakeJSONDataRecreation {
    private JSONObject data;
    private final JSONArray array = new JSONArray();
    private final JSONObject item1 = new JSONObject();
    private final JSONObject item2 = new JSONObject();
    private final JSONObject item3 = new JSONObject();

    public JSONObject getData() {
        JSONObject json = new JSONObject();
        insertData(
                1,
                "Art: 1 New Museum",
                "Jl. Rajawali Selatan Raya no. 3 Gn. Sahari Utara, Sawah Besar",
                "Berekreasi mengunjungi museum bisa menjadi pilihan untuk Anda yang sedang mencari hiburan di ibu kota. Berbagai koleksi seni maupun bersejarah bisa kalian nikmati sekaligus menambah wawasan seputar seni. Di Jakarta, ada satu museum baru yang bisa Anda datangi untuk melihat berbagai koleksi menarik, yakni Art:1 New Museum.",
                "-6.1469940",
                "106.8402340",
                "https://imgcdn.rri.co.id/__srct/cfb3228334b52d754373c1e819e9a6cc/750358/49ab39e53f69a75fbec5eb3b15c46adc.jpeg?v=1.0.3",
                item1
        );
        insertData(
                2,
                "Dieng Plateu Museum (Museum Kaliasa Dieng)",
                "Dalam  Kompleks  Percandian  Dieng",
                "Selain Dieng Plateau Theater, inilah tempat yang harus dikunjungi sebelum mengeksplorasi kawasan Dataran Tinggi Dieng. Museum Dieng Plateu yang terletak di Desa Dieng Kulon, Kecamatan Batur, Kabupaten Banjarnegara, menyimpan berbagai hal yang berkaitan dengan Dieng.",
                "-7.2087500",
                "109.9053040",
                "https://www.korinatour.co.id/wp-content/uploads/2018/09/3__Bangunan_yang_berada_di_bagian_depan_dibangun_pada_tahun_1984__Bangunan_ini_menyimpan_berbagai_benda_yang_terkait_dengan_candi.jpg",
                item2
            );
        insertData(
                3,
                "Herbarium Bogoriensi Museum",
                "Jl. Ir. H. Juanda 22",
                "Museum yang berkaitan dengan penemuan tanaman di Indonesia yang terkenal hingga ke luar negeri. Seperti pala, rempah, ada terkait dengan kayu gaharu, beberapa hewan yang di awetkan seperti macan kumbang, harimau Sumatera, beruang madu, tanaman2 yang di awetkan kering dan basah, alat2 untuk meneliti tanaman seperti berbagai macam mikroskop, dsb.",
                "-6.5990800",
                "106.7937480",
                "https://cimanggubogor.com/wp-content/uploads/2018/09/foto-herbarium-bogoriensis.jpg",
                item3
        );
        array.put(item1);
        array.put(item2);
        array.put(item3);
        try {
            json.put("data", array);
            this.data = json;
            return this.data;
        } catch (JSONException e) {
            e.printStackTrace();
            return this.data;
        }
    }

    private void insertData(int id, String name, String address, String desc, String lat, String longitude, String urlImage, JSONObject item) {
        try {
            item.put("Id", id);
            item.put("Name", name);
            item.put("Address", address);
            item.put("Desc", desc);
            item.put("Lat", lat);
            item.put("Long", longitude);
            item.put("UrlImage", urlImage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
