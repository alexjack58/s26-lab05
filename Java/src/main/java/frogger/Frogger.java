package frogger;

/**
 * Refactor Task 1 & 2: Frogger
 *
 * @author Zishen Wen (F22), Deyuan Chen (S22), Duan Liang (F23)
 */
// Task 1
// Problem: Originallty, frogger class reached into Road and grabbed boolean[] arr
// that task should belong to Road; checking if positions are valid - Feature Envy
// if road changes how it stores, Frogger breaks and needs to change too
// also high coupling
//solution: remove getOccupied, and have Road provide methods to check if pos is valid and occupied
// information expert principle: road should answer questions about its own state, not Frogger

// Task 2
// Frogger had long list with parameters, with 6 fields
// Have to change entire constructor if we want to add a new field
// Solution: use FroggerID parameter obkect to store identity info, pass in 1 object instead
public class Frogger {

    // Field for task 1.
    private final Road road;
    private int position;
    
    // Field for task 2.
    private final Records records;
    private FroggerID id;

    public Frogger(Road road, int position, Records records, String firstName, String lastName, String phoneNumber,
    String zipCode, String state, String gender) {
        this.road = road;
        this.position = position;
        this.records = records;
        this.id = new FroggerID(firstName, lastName, phoneNumber, zipCode, state, gender);
    }

    /**
     * Moves Frogger.
     *
     * @param forward true is move forward, else false.
     * @return true if move successful, else false.
     */
    public boolean move(boolean forward) {
        int nextPosition = this.position + (forward ? 1 : -1);
        if (!road.isValidPosition(nextPosition) || road.isOccupied(nextPosition)) {
            return false;
        }
        this.position = nextPosition;
        return true;
    }

    /**
     * Records Frogger to the list of records.
     * 
     * @return true if record successful, else false.
     */
    public boolean recordMyself() {
        return records.addRecord(id);
    }

}