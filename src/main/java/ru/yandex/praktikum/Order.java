package ru.yandex.praktikum;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import java.time.LocalDate;
import java.util.*;

public class Order {
    public static String black;
    public static String grey;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public Integer getMetroStation() {
        return metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getRentTime() {
        return rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public List<String> getColor() {
        return color;
    }

    private String firstName;
    private String lastName;
    private String address;
    private Integer metroStation;
    private String phone;
    private Integer rentTime;
    private String deliveryDate;
    public String comment;

    public void setColor(List<String> color) {
        this.color = color;
    }

    public List<String> color;

    public Order(String firstName, String lastName, String address, Integer metroStation, String phone, Integer rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public static Order getRandomOrder() {
        String firstName = RandomStringUtils.randomAlphabetic(5);
        String lastName = RandomStringUtils.randomAlphabetic(5);
        String address = RandomStringUtils.randomAlphabetic(5);
        Integer metroStation = RandomUtils.nextInt();
        String phone = RandomStringUtils.randomNumeric(11);
        Integer rentTime = RandomUtils.nextInt();
        LocalDate deliveryDate = LocalDate.now();
        String comment = RandomStringUtils.randomAlphabetic(5);
        List<String> color = new ArrayList<>();
        color.add(black);
        color.add(grey);
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate.toString(), comment, (ArrayList<String>) color);
    }

}
