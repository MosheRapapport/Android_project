package com.example.my.model.entities;

public class Pack {
    private PackType packType;
    private PackWeight packWeight;
    private boolean packFragile;
    private Person recipient;
    private AddressAndLocation storageLocation;

    public Pack(PackType packType, PackWeight packWeight, boolean packFragile, Person recipient, AddressAndLocation storageLocation) {
        this.packType = packType;
        this.packWeight = packWeight;
        this.packFragile = packFragile;
        this.recipient = recipient;
        this.storageLocation = storageLocation;
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

}
