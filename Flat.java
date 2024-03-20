import java.time.LocalDate;

// Represents a flat, inherits from Premises
public final class Flat extends Premises {
    private String flatNumber; // Flat number
    private int floorNumber; // Floor number

    // Constructor to initialize flat details
    public Flat(String flatNumber, int floorNumber, String street, String buildingNumber, String town, String postCode, double size, int cost, LocalDate expDate) {
        super(street, buildingNumber, town, postCode, size, cost, expDate); // Call superclass constructor
        this.flatNumber = flatNumber; // Initialize flat number
        this.floorNumber = floorNumber; // Initialize floor number
    }

    // Method to generate string representation of flat
    public String toString() {
        return "Flat: street " + street + ", building number " + buildingNumber + ", flat number " + flatNumber + ", floor number " + floorNumber + ", town " + town + ", post code " + postCode + ", size " + size + ", cost " + cost + ", offer validity date" + expDate;
    }

    // Getter for flat number
    public String getFlatNumber() {
        return flatNumber;
    }

    // Getter for floor number
    public int getFloorNumber() {
        return floorNumber;
    }

    // Setter for flat number
    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    // Setter for floor number
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
}
