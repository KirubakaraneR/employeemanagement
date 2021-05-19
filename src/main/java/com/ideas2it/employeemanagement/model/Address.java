package com.ideas2it.employeemanagement.model;

import com.ideas2it.employeemanagement.model.Employee;

/**
 * POJO class for address
 * It contains employee address details such as id, doorNumber, street,
 * nagar, district, state, country, pin code
 *
 * @version 1.0 04-05-2021
 * @author Kirubakarane R
 */
public class Address {
    private int id;
    private String doorNumber;
    private String street;
    private String nagar;
    private String city;
    private String district;
    private String state;
    private String country;
    private int pinCode;
    private String addressType;
    private boolean isDeleted;
    private Employee employee;

    /**
     * Here we get the employee address details
     *
     * @param doorNumber - resident door number
     * @param street - resident street 
     * @param nagar - residential nagar
     * @param city - residential city
     * @param district - residential district
     * @param state - residential state
     * @param country - residential country
     * @param pinCode - residential pin code
     * @param addressType - Permanent or temporary 
     * @param employeeId - Employee id
     * @param addressId - Address id
     */
    public Address(String doorNumber, String street, 
            String nagar, String city, String district, String state, 
            String country, int pinCode, String addressType) {
        this.doorNumber = doorNumber;
        this.street = street;
        this.nagar = nagar;
        this.city = city;
        this.district = district;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.addressType = addressType;
    }

    public Address() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
  
    public String getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }
 
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    } 

    public String getNagar() {
        return nagar;
    }
 
    public void setNagar(String nagar) {
        this.nagar = nagar;
    }

    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {  
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
  
    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}