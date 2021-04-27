package com.ideas2it.employeemanagement.model;

/**
 * POJO class for address
 * It contains employee address details such as id, doorNumber, street,
 * nagar, district, state, country, pin code
 *
 * @version 1.0 12 March 2021
 * @author Kirubakarane R
 */
public class Address {
    private String doorNumber;
    private String street;
    private String nagar;
    private String city;
    private String district;
    private String state;
    private String country;
    private int pinCode;
    private String addressType;
    private String employeeId;
    private int addressId;

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
    public Address(String employeeId, String doorNumber, String street, 
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
        this.employeeId = employeeId;
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
 
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
 
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String toString() {
        return "\nAddress      : No:" + this.doorNumber + "," + this.street + ","
                + "\n\t      " + this.nagar + "," + this.city + ",\n\t"
                + "      " +this.district + ",\n\t      " + this.state + "-" 
                + this.pinCode + ",\n\t      " + this.country + "\n";
    }
}