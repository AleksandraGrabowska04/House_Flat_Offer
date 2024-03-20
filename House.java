import java.time.LocalDate;

// Represents a house, inherits from Premises
public final class House extends Premises {
    private double parcelSize; // Size of the parcel of land

    // Constructor to initialize house details
    public House(double parcelSize, String street, String buildingNumber, String town, String postCode, double size, int cost, LocalDate expDate) {
        super(street, buildingNumber, town, postCode, size, cost, expDate); // Call superclass constructor
        this.parcelSize = parcelSize; // Initialize parcel size
    }

    // Method to generate string representation of house
    public String toString() {
        return "House: street " + street + ", building number " + buildingNumber + ", town " + town + ", post code " + postCode + ", size " + size + ", cost " + cost + ", offer validity date " + expDate + ", parcel size " + parcelSize;
    }

    // Getter for parcel size
    public double getParcelSize() {
        return parcelSize;
    }

    // Setter for parcel size
    public void setParcelSize(float parcelSize) {
        this.parcelSize = parcelSize;
    }
}
