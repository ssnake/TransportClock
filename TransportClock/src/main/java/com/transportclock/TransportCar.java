package com.transportclock;

/**
 * Created by snake on 11.01.14.
 */
public class TransportCar {
    int id;
    int route_id;
    String name;
    Float speed;
    Float lat;
    Float lng;
    Float angle;
    Boolean avaible;

    public TransportCar(Float lat, Float lng) {
        this();
        this.lat = lat;
        this.lng = lng;

    }

    public TransportCar() {
        avaible = false;
        speed = 0.0f;
        lat = 0.0f;
        lng = 0.0f;
        angle = 0.0f;
        name = "";


    }

    public Boolean getAvaible() {
        return avaible;
    }

    public void setAvaible(Boolean avaible) {
        this.avaible = avaible;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Float getAngle() {
        return angle;
    }

    public void setAngle(Float angle) {
        this.angle = angle;
    }

    public String toRaw() {
        return String.format("id=%d\nroute_id=%d\nname=%s\nspeed=%f\nangle=%f\navaible=%s", id, route_id, name, speed, angle, avaible.toString());
    }

    public RoutePoint getPoint() {
        return new RoutePoint(getLat(), getLng());
    }

    public void setPoint(RoutePoint carPoint) {
        setLat(carPoint.getLat());
        setLng(carPoint.getLng());
    }
}
