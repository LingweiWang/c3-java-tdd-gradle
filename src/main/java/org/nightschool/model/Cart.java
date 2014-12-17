package org.nightschool.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by apple on 14-12-16.
 */
public class Cart {

    private final List<Disk> disks = new ArrayList<>();

    private Disk hardDisk = new Disk("1TB大容量光盘","imgurl","description",100.00);

    private Disk marriageDisk = new Disk("婚庆光盘", "imgurl","description",80.00);

    private Disk freshDisk = new Disk("小清新光盘", "imgurl", "description",100.00);

    public List<Disk> getDisks() {
        return disks;
    }

    public void add(Disk disk) {
        disks.add(disk);
    }

    public int countKinds() {
        HashSet<Disk> diskSet = new HashSet<>();
        disks.forEach(o -> diskSet.add(o));
        return diskSet.size();
    }

    public int promotionOfFreshDisk() {
        int count = amountOfCertainDisk(freshDisk);
        if(count >= 2){
            return count/2;
        }else{
            return 0;
        }

    }

    public double promotionOfMarriageDisk() {
        int count = amountOfCertainDisk(marriageDisk);
        if(count*marriageDisk.getPrice() >= 100){
            return (int)(count*marriageDisk.getPrice()/100)*10.00;
        }else{
            return 0;
        }

    }

    public double promotionOfHardDisk() {
        int count = amountOfCertainDisk(hardDisk);
        return count*hardDisk.getPrice()*0.05;
    }

    public int amountOfCertainDisk(Disk certainDisk){
        int count = disks.stream().filter(o -> o.equals(certainDisk)).collect(Collectors.toList()).size();

        return count;
    }

    public double settleAccounts() {
        double price = 0;
        for(Disk disk : disks){
            price += disk.getPrice();
        }
        price = price - promotionOfMarriageDisk() - promotionOfHardDisk();
        for(int i = 0; i < promotionOfFreshDisk(); i++){
            disks.add(freshDisk);
        }

        return price;
    }

    public void deleteDisks(Disk disk) {
        int i = 0 ;
        while(i<amountOfCertainDisk(disk)){
            disks.remove(disk);
            i++;
        }
    }
}
