import java.util.ArrayList;
import java.util.function.Predicate;

// Class representing a list of property offers
public class OfferList {
    private ArrayList<Premises> offers; // List to store property offers

    // Constructor to initialize the list of offers
    public OfferList() {
        this.offers = new ArrayList<>();
    }

    // Method to filter offers based on a given predicate
    public ArrayList<Premises> getFiltered(Predicate<Premises> predicate) {
        ArrayList<Premises> filteredList = new ArrayList<>();
        for (Premises premises : offers) {
            if (predicate.test(premises)) { // Test if the predicate holds true for the premises
                filteredList.add(premises); // If true, add the premises to the filtered list
            }
        }
        return filteredList; // Return the filtered list
    }

    // Method to add a flat offer to the list
    public void addOffer(Flat flat) {
        offers.add(flat); // Add the flat offer to the list
    }

    // Method to add a house offer to the list
    public void addOffer(House house) {
        offers.add(house); // Add the house offer to the list
    }
}
