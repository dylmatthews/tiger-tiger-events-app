package com.dylanmatthewsware.tigertigerdurbanevents;

/**
 * Created by home on 2017/11/21.
 */

public class Event {
    public String endTime;
        public String startTime;
        public String name;
        public String nameOfPlace;
        public Double latitude;
        public String city;
        public String country;
        public String street;
        public String zip;
        public Double longitude;
        public String description;

        public Event()
        {

        }

        public Event(String name, String description, String startTime, String endTime, String hostName, String city,
                     String country, Double latitude, Double longitude, String streetAddress, String zip) {
            this.name = name;
            this.description = description;
            this.startTime = startTime;
            this.endTime = endTime;
            this.nameOfPlace = hostName;
            this.city = city;                                               //Event constructor
            this.country = country;
            this.latitude = latitude;
            this.longitude  = longitude;
            this.street = streetAddress;
            this.zip = zip;


        }


        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNameOfPlace() {
            return nameOfPlace;
        }

        public void setNameOfPlace(String nameOfPlace) {
            this.nameOfPlace = nameOfPlace;                                     //Varible getter and setters
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }


        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }
}
