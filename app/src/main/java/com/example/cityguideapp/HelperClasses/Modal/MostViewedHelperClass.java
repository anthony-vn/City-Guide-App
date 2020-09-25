package com.example.cityguideapp.HelperClasses.Modal;

public class MostViewedHelperClass {
    private int image_ms;
    private String title_ms, description_ms;

    public MostViewedHelperClass(int image_ms, String title_ms, String description_ms) {
        this.image_ms = image_ms;
        this.title_ms = title_ms;
        this.description_ms = description_ms;
    }

    public int getImage_ms() {
        return image_ms;
    }

    public void setImage_ms(int image_ms) {
        this.image_ms = image_ms;
    }

    public String getTitle_ms() {
        return title_ms;
    }

    public void setTitle_ms(String title_ms) {
        this.title_ms = title_ms;
    }

    public String getDescription_ms() {
        return description_ms;
    }

    public void setDescription_ms(String description_ms) {
        this.description_ms = description_ms;
    }
}
