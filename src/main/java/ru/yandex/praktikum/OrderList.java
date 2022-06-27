package ru.yandex.praktikum;
import java.util.Date;
import java.util.List;

public class OrderList {

    private Integer id;
        private Integer courierId;
        private String firstName;
        private String lastName;
        private String address;
        private String metroStation;
        private String phone;
        private Integer rentTime;
        private Date deliveryDate;
        private Integer track;
        private List<String> color;
        private String comment;
        private Date createdAt;
        private Date updatedAt;
        private Integer status;

    public OrderList(Integer id, Integer courierId, String firstName, String lastName, String address, String metroStation, String phone, Integer rentTime, Date deliveryDate, Integer track, List<String> color, String comment, Date createdAt, Date updatedAt, Integer status) {
        this.id = id;
        this.courierId = courierId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.track = track;
        this.color = color;
        this.comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }
}
