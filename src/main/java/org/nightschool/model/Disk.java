package org.nightschool.model;

/**
 * Created by apple on 14-12-16.
 */
public class Disk {
    private String name;
    private String img;
    private String desc;
    private double price;

    public Disk(String name, String img, String desc,double price) {
        this.name = name;
        this.img = img;
        this.desc = desc;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public String getImg() {
        return this.img;
    }

    public String getDesc() {
        return this.desc;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Disk))
            return false;
        else{
            Disk disk = (Disk) obj;
            return name.equals(disk.getName()) && img.equals(disk.getImg()) && desc.equals(disk.getDesc()) && price == disk.getPrice() ;
        }
    }
}
