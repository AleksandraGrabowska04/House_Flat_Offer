import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

// Main class to manage property offers
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner sc_num = new Scanner(System.in);
        OfferList currentOffers = new OfferList();
        addSamples(currentOffers);
        boolean wantToContinue = true;

        System.out.println("The program handles offers for sale of flats and houses");

        while (wantToContinue) {
            menu(); // Display menu
            int choice = sc_num.nextInt(); // Get user choice

            switch (choice) {
                case 1 -> menuAddOffer(sc, sc_num, currentOffers); // Add offer
                case 2 -> menuAllCurrentOffers(currentOffers); // View all offers
                case 3 -> menuAllCurrentOffersHouse(currentOffers); // View house offers
                case 4 -> menuAllCurrentOffersFlat(currentOffers); // View flat offers
                case 5 -> menuAllCurrentOffersHouseTownSize(sc, sc_num, currentOffers); // Filter house offers
                case 6 -> menuAllCurrentOffersFlatTownCostFloor(sc, sc_num, currentOffers); // Filter flat offers
                case 7 -> { // Exit
                    wantToContinue = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid option. Choose again."); // Invalid choice
            }
        }
    }

    // Display menu
    static void menu() {
        System.out.println("====MENU====");
        System.out.println("1. Add offer for a flat or house");
        System.out.println("2. View current offers for houses and flats");
        System.out.println("3. View current offers for houses");
        System.out.println("4. View current offers for flats");
        System.out.println("5. View current offers for houses in a given town and with size not less than provided");
        System.out.println("6. View current offers for flats in a given town, not more expensive than provided price, and on a floor equal to or above provided");
        System.out.println("7. Exit");
    }

    // Add sample offers
    static void addSamples(OfferList currentOffers) {
        Flat flat1 = new Flat("10A", 1, "Flowers", "1A", "Warsaw", "11-111", 111.11, 1000000, LocalDate.of(2025, 10, 10));
        Flat flat2 = new Flat("20B", 2, "Apples", "2B", "Gdynia", "22-22", 222.22, 2000000, LocalDate.of(2024, 11, 11));

        currentOffers.addOffer(flat1);
        currentOffers.addOffer(flat2);

        House house1 = new House(100.55, "Green", "11", "Reda", "11-111", 111.11, 9000000, LocalDate.of(2024, 12, 30));
        House house2 = new House(20000.55, "Red", "22", "Gdansk", "22-22", 222.22, 29000000, LocalDate.of(2024, 10, 3));

        currentOffers.addOffer(house1);
        currentOffers.addOffer(house2);
    }

    // Read common data for offers
    static Object[] readCommonData(Scanner sc, Scanner sc_num) {
        System.out.println("Enter street: ");
        String street = sc.nextLine();

        System.out.println("Enter building number: ");
        String buildingNumber = sc.next();

        System.out.println("Enter town: ");
        String town = sc.next();

        System.out.println("Enter postal code in format XX-XXX: ");
        String postCode = sc.next();

        System.out.println("Enter size in format e.g. 1000.1: ");
        double size = sc_num.nextDouble();

        System.out.println("Enter price in format e.g. 1000000: ");
        int cost = sc_num.nextInt();

        sc.nextLine();
        System.out.println("Enter offer validity date in format YYYY-mm-dd: ");
        String expDateString = sc.nextLine();
        LocalDate expDate = LocalDate.parse(expDateString);

        return new Object[]{street, buildingNumber, town, postCode, size, cost, expDate};
    }

    // Add offer menu
    static void menuAddOffer(Scanner sc, Scanner sc_num, OfferList currentOffers) {

        System.out.println("Choose property type (1 - Flat, 2 - House): ");
        int eventType = sc_num.nextInt();
        double defaultSize = 100.1;

        switch (eventType) {
            case 1 -> addOffers(sc, sc_num, currentOffers); // Add flat offer
            case 2 -> addOffers(sc, sc_num, currentOffers, defaultSize); // Add house offer
            default -> System.out.println("Invalid property type.");
        }
    }

    // Add flat offer
    static void addOffers(Scanner sc, Scanner sc_num, OfferList currentOffers) {
        System.out.println("Enter flat number: ");
        String flatNumber = sc.nextLine();

        System.out.println("Enter floor number: ");
        int floorNumber = sc_num.nextInt();

        Object[] commonData = readCommonData(sc, sc_num);

        Flat flat = new Flat(flatNumber, floorNumber, (String) commonData[0], (String) commonData[1],
                (String) commonData[2], (String) commonData[3], (Double) commonData[4], (Integer) commonData[5],
                (LocalDate) commonData[6]);
        currentOffers.addOffer(flat);

        System.out.println("Flat added!");
    }

    // Add house offer
    static void addOffers(Scanner sc, Scanner sc_num, OfferList currentOffers, double defaultSize) {
        System.out.println("Enter parcel size in format e.g. 1000.55: ");
        float parcelSize = sc_num.nextFloat();

        Object[] commonData = readCommonData(sc, sc_num);

        House house = new House(parcelSize, (String) commonData[0], (String) commonData[1], (String) commonData[2],
                (String) commonData[3], (Double) commonData[4], (Integer) commonData[5], (LocalDate) commonData[6]);
        currentOffers.addOffer(house);

        System.out.println("House added!");
    }

    // View all current offers
    static void menuAllCurrentOffers(OfferList currentOffers) {
        ArrayList<Premises> premiOffers = currentOffers.getFiltered(premi -> premi.getExpDate().isAfter(LocalDate.now()) || premi.getExpDate().equals(LocalDate.now()));
        if (!premiOffers.isEmpty()) {
            System.out.println("Current offers: ");
            for (Premises premi : premiOffers) {
                System.out.println("===========");
                System.out.println(premi);
            }
        } else {
            System.out.println("No offers available.");
        }
    }

    // View current house offers
    static void menuAllCurrentOffersHouse(OfferList currentOffers) {
        ArrayList<Premises> premiOffers = currentOffers.getFiltered(premi -> premi.getExpDate().isAfter(LocalDate.now()) || premi.getExpDate().equals(LocalDate.now()));
        if (!premiOffers.isEmpty()) {
            System.out.println("Current house offers: ");
            for (Premises premi : premiOffers) {
                if (premi instanceof House) {
                    System.out.println("===========");
                    System.out.println(premi);
                }
            }
        } else {
            System.out.println("No house offers available.");
        }
    }

    // View current flat offers
    static void menuAllCurrentOffersFlat(OfferList currentOffers) {
        ArrayList<Premises> premiOffers = currentOffers.getFiltered(premi -> premi.getExpDate().isAfter(LocalDate.now()) || premi.getExpDate().equals(LocalDate.now()));
        if (!premiOffers.isEmpty()) {
            System.out.println("Current flat offers: ");
            for (Premises premi : premiOffers) {
                if (premi instanceof Flat) {
                    System.out.println("===========");
                    System.out.println(premi);
                }
            }
        } else {
            System.out.println("No flat offers available.");
        }
    }

    // Filter current house offers by town and size
    static void menuAllCurrentOffersHouseTownSize(Scanner sc, Scanner sc_num, OfferList currentOffers) {
        LocalDate currentDate = LocalDate.now();

        System.out.println("Enter town: ");
        String filterTown = sc.next();
        System.out.println("Enter size in format e.g. 100.53: ");
        double filterSize = sc_num.nextDouble();

        ArrayList<Premises> premiOffers = currentOffers.getFiltered(premi ->
                (premi.getExpDate().isAfter(currentDate) || premi.getExpDate().equals(LocalDate.now()))
                        && premi.getTown().equals(filterTown)
                        && premi.getSize() >= filterSize);
        if (!premiOffers.isEmpty()) {
            System.out.println("Current house offers: ");
            for (Premises premi : premiOffers) {
                if (premi instanceof House) {
                    System.out.println("===========");
                    System.out.println(premi);
                }
            }
        } else {
            System.out.println("No house offers available.");
        }
    }

    // Filter current flat offers by town, cost, and floor
    static void menuAllCurrentOffersFlatTownCostFloor(Scanner sc, Scanner sc_num, OfferList currentOffers) {
        LocalDate currentDate = LocalDate.now();

        System.out.println("Enter town: ");
        String filterTown = sc.next();
        System.out.println("Enter price in format e.g. 10000: ");
        int filterCost = sc_num.nextInt();
        System.out.println("Enter floor number: ");
        int filterFloor = sc_num.nextInt();

        ArrayList<Premises> premiOffers = currentOffers.getFiltered(premi -> (premi.getExpDate().isAfter(currentDate) || premi.getExpDate().equals(LocalDate.now())) && premi.getTown().equals(filterTown) && premi.getCost() <= filterCost && ((Flat) premi).getFloorNumber() >= filterFloor);
        if (!premiOffers.isEmpty()) {
            System.out.println("Current flat offers: ");
            for (Premises premi : premiOffers) {
                if (premi instanceof Flat) {
                    System.out.println("===========");
                    System.out.println(premi);
                }
            }
        } else {
            System.out.println("No flat offers available.");
        }
    }
}

