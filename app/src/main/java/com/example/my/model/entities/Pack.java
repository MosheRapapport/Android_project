package com.example.my.model.entities;

import java.time.LocalDate;

public class Pack {
    private PackType packType;
    private PackWeight packWeight;
    private boolean packFragile;
    private Person recipient;
    private AddressAndLocation storageLocation;
    private PackStatus packStatus;
    private LocalDate deliveryDate;
    private LocalDate receivedDate;
    private String deliveryName;
    private  String aKey;

    public Pack() {
    }

    public Pack(PackType packType, PackWeight packWeight, boolean packFragile, Person recipient, AddressAndLocation storageLocation, PackStatus packStatus, LocalDate deliveryDate, LocalDate receivedDate, String deliveryName) {
        this.packType = packType;
        this.packWeight = packWeight;
        this.packFragile = packFragile;
        this.recipient = recipient;
        this.storageLocation = storageLocation;
        this.packStatus = packStatus;
        this.deliveryDate = deliveryDate;
        this.receivedDate = receivedDate;
        this.deliveryName = deliveryName;
    }



    public PackType getPackType() {
        return packType;
    }

    public void setPackType(PackType packType) {
        this.packType = packType;
    }

    public PackWeight getPackWeight() {
        return packWeight;
    }

    public void setPackWeight(PackWeight packWeight) {
        this.packWeight = packWeight;
    }

    public boolean isPackFragile() {
        return packFragile;
    }

    public void setPackFragile(boolean packFragile) {
        this.packFragile = packFragile;
    }

    public Person getRecipient() {
        return recipient;
    }

    public void setRecipient(Person recipient) {
        this.recipient = recipient;
    }

    public AddressAndLocation getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(AddressAndLocation storageLocation) {
        this.storageLocation = storageLocation;
    }

    public PackStatus getPackStatus() { return packStatus; }

    public void setPackStatus(PackStatus packStatus) { this.packStatus = packStatus; }

    public LocalDate getDeliveryDate() { return deliveryDate; }

    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }

    public LocalDate getReceivedDate() { return receivedDate; }

    public void setReceivedDate(LocalDate receivedDate) { this.receivedDate = receivedDate; }

    public String getDeliveryName() { return deliveryName; }

    public void setDeliveryName(String deliveryName) { this.deliveryName = deliveryName; }

    public String getaKey() { return aKey; }

    public void setaKey(String aKey) { this.aKey = aKey; }


}
