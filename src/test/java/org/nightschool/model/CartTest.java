package org.nightschool.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by apple on 14-12-16.
 */
public class CartTest {

    private Cart cart;
    private Disk freshDisk;
    private Disk marriageDisk;
    private Disk hardDisk;

    @Before
    public void setUp() throws Exception {
        cart = new Cart();
        freshDisk = new Disk("小清新光盘", "imgurl", "description",100.00);
        marriageDisk = new Disk("婚庆光盘", "imgurl","description",80.00);
        hardDisk = new Disk("1TB大容量光盘","imgurl","description",100.00);
    }

    @Test
    public void should_cart_be_empty_when_no_disk_added() throws Exception {
        List<Disk> disks = cart.getDisks();
        assertThat(disks.size(), is(0));

    }

    @Test
    public void should_cart_be_able_to_add_disk_to_cart() throws Exception

    {
        Disk disk = new Disk("","","",0.00);
        cart.add(disk);
        List<Disk> disks = cart.getDisks();

        assertThat(disks.size(), is(1));
    }

    @Test
    public void should_be_able_to_check_disk_attr() throws Exception {
        Disk disk = new Disk("","","",0.00);
        disk.setName("aaa");
        disk.setImg("bbb");
        disk.setDesc("ccc");
        cart.add(disk);

        List<Disk> disks = cart.getDisks();

        assertThat(disks.get(0).getName(),is("aaa"));
        assertThat(disks.get(0).getImg(),is("bbb"));
        assertThat(disks.get(0).getDesc(),is("ccc"));
    }

    @Test
    public void should_be_able_to_count_kinds_of_disks() throws Exception {
        cart.add(freshDisk);
        cart.add(marriageDisk);

        assertThat(cart.countKinds(),is(2));

    }

    @Test
    public void should_be_able_to_remove_a_disk_from_cart() throws Exception {
        cart.add(freshDisk);
        cart.add(marriageDisk);
        cart.add(hardDisk);

        cart.deleteDisks(freshDisk);

        assertThat(cart.getDisks().size(), is(2));
    }

    @Test
    public void should_there_be_1_more_when_buy_2_freshDisk() throws Exception {
        cart.add(freshDisk);
        cart.add(freshDisk);

        int count = cart.promotionOfFreshDisk();
        assertThat(count, is(1));
    }

    @Test
    public void should_there_be_1_more_when_buy_3_freshDisk() throws Exception {
        cart.add(freshDisk);
        cart.add(freshDisk);
        cart.add(freshDisk);

        int count = cart.promotionOfFreshDisk();

        assertThat(count,is(1));
    }

    @Test
    public void should_there_always_be_2_plus_1_when_buy_freshDisk() throws Exception {
        int n = (int) Math.random();
        for(int i = 0; i < n; i++){
            cart.add(freshDisk);
        }
        
        int count = cart.promotionOfFreshDisk();
        assertThat(count,is((int)(count *0.5)));

    }

    @Test
    public void should_there_be_10_bonus_when_buy_100_bucks_marriageDisk() throws Exception {
        cart.add(marriageDisk);
        cart.add(marriageDisk);

        double charge = cart.promotionOfMarriageDisk();

        assertThat(charge, is(10.00));

    }

    @Test
    public void should_there_be_20_bonus_when_buy_200_bucks_marriageDisk() throws Exception {
        cart.add(marriageDisk);
        cart.add(marriageDisk);
        cart.add(marriageDisk);

        double charge = cart.promotionOfMarriageDisk();

        assertThat(charge, is(20.00));
    }

    @Test
    public void should_there_be_95percent_sale_when_buy_1TBhardDisk() throws Exception {
        cart.add(hardDisk);

        double charge = cart.promotionOfHardDisk();

        assertThat(charge, is(5.00));
    }

    @Test
    public void test_count() throws Exception {
        cart.add(hardDisk);
        cart.add(marriageDisk);
        cart.add(marriageDisk);
        cart.add(freshDisk);
        cart.add(freshDisk);
        double charge = cart.settleAccounts();
        assertThat(charge, is(445.00));
    }


}
