import java.util.List;
import java.util.ArrayList;

public class elevator {
    private int currentFloor; 
    private int highestFloor; 
    private int lowestFloor; 
    private int maxElevatorWeight; 
    private List<Integer> floorQueue;
    private enum Direction {
        UP,
        DOWN,
        IDLE
    }
    private Direction direction;
    


    public elevator(int currentFloor, int highestFloor, int lowestFloor, int maxElevatorWeight) {
        this.currentFloor = currentFloor;
        this.highestFloor = highestFloor;
        this.lowestFloor = lowestFloor;
        this.maxElevatorWeight = maxElevatorWeight;
        this.floorQueue = new ArrayList<>();
        this.direction = Direction.IDLE;
    }

    // So I never thought about how an elevator actually works, but I think it works something like a queue. 

    // second thought, let's say that a more efficient pathing exists. Let's say that one person is on the top 
    // floor and wants to go to the bottom floor. While they are traveling, another person on the 3rd floor wants to 
    // go down, and after them a person on the 5 floor wants to also go down. We should pick up the 5th floor guy first
    // this sounds more like a priority queue. 

    // Let me think about how elevators work. I've been in a lot of them.

    // nah its not a priority queue. That doesn't make sense. reversing directions would make the priority
    // flip, which is a pain. I think it would be better to keep it simple and use a list.



    public void setFloor(int floor) {

    }

    public int getFloor(){
        return currentFloor;
    }

    public void requestFloor(int start, int destination){
        if(destination > highestFloor || destination < lowestFloor){
            throw new IllegalArgumentException("Pick a valid destination");

        }
        if(start > highestFloor || start < lowestFloor){
            throw new IllegalArgumentException("Pick a valid start");
        }
        if(start == destination){
            throw new IllegalArgumentException("Start and destination floors are the same");
        }
        floorQueue.add(destination);
        setDirection();
        

       
    }

    public void setDirection(){
        if(floorQueue.isEmpty()){
            direction = Direction.IDLE;
        }
        if(floorQueue.get(0) > currentFloor && direction == Direction.IDLE){
            direction = Direction.UP;
        }
        if(floorQueue.get(0) < currentFloor && direction == Direction.IDLE){
            direction = Direction.DOWN;
        }



}

public void move(){
    if(direction == Direction.UP){
}   




}}



public static void main(String[] args) {
    System.out.println("Hello, World!");
}