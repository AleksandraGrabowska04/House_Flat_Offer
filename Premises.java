import java.time.LocalDate;

// Abstract class representing premises
sealed public abstract class Premises permits Flat, House {
    protected String street; // Street name
    protected String buildingNumber; // Building number
    protected String town; // Town name
    protected String postCode; // Postal code
    protected double size; // Size of premises
    protected int cost; // Cost of premises
    protected LocalDate expDate; // Expiry date of the offer

    // Constructor to initialize premises details
    public Premises(String street, String buildingNumber, String town, String postCode, double size, int cost, LocalDate expDate) {
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.town = town;
        this.postCode = postCode;
        this.size = size;
        this.cost = cost;
        this.expDate = expDate;
    }

    // Getter method for street name
    public String getStreet() {
        return street;
    }

    // Getter method for building number
    public String getBuildingNumber() {
        return buildingNumber;
    }

    // Getter method for town name
    public String getTown() {
        return town;
    }

    // Getter method for postal code
    public String getPostCode() {
        return postCode;
    }

    // Getter method for premises size
    public double getSize() {
        return size;
    }

    // Getter method for premises cost
    public double getCost() {
        return cost;
    }

    // Getter method for expiry date of the offer
    public LocalDate getExpDate() {
        return expDate;
    }
}
