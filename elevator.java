import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;



public class elevator {
    private int currentFloor; 
    private int highestFloor; 
    private int lowestFloor; 

    private List<Integer> upList;
    private List<Integer> downList;
    private enum Direction {
        UP,
        DOWN,
        IDLE
    }
    private Direction direction;
    


    public elevator(int currentFloor, int highestFloor, int lowestFloor) {
        this.currentFloor = currentFloor;
        this.highestFloor = highestFloor;
        this.lowestFloor = lowestFloor;
        this.upList = new ArrayList<>();
        this.downList = new ArrayList<>();
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
    

    // hang on what if i use TWO lists?! one for up and one for down. Let's think about this case: 
    // the elevator is already on floor 2 or something and is going down and there is a person on floor 5 wanting to go down.
    // this should queue the person on floor 5 but it shouldn't execute until after the elevator up cycle happens.
    // hmm.
    // This is actually harder than I thought. What if I use 4 lists?
    // an up list going up, a down list going down, an up list going down, and a down list going up?


    // commit 5 has two functions requestfloor and requestelevator. Can these be combined? The elevator
    // doesn't need to know if we are going to a person or dropping someone off, only the integer for the floor.


    public void openDoor(){
        System.out.println("Door opened at floor " + currentFloor);
    }

    public int getFloor(){
        return currentFloor;
    }

    public void requestFloor(int destination){
        if(destination > highestFloor || destination < lowestFloor){
            throw new IllegalArgumentException("Pick a valid destination");

        }
        if(destination == currentFloor){
            throw new IllegalArgumentException("You're already on that floor");
        }
        if (upList.contains(destination) || downList.contains(destination)) {
            System.out.println("Duplicate floor request");
            return;
        }

        if(destination > currentFloor){
            if(downList.isEmpty()){
                direction = Direction.UP;
            }
            upList.add(destination);
            Collections.sort(upList);
        }
        else{
            if(upList.isEmpty()){
                direction = Direction.DOWN;
            }
            downList.add(destination);
            Collections.sort(downList);
            Collections.reverse(downList);
        }

        if (direction == Direction.IDLE) {
            if (!upList.isEmpty()){
                direction = Direction.UP;
            }
            else if (!downList.isEmpty()){
                direction = Direction.DOWN;
            }
        }

        
       
    }


    public void move(){
        if(upList.isEmpty() && downList.isEmpty()){
            direction = Direction.IDLE;
            return;
        }
        
        if(direction == Direction.UP){
            if(upList.isEmpty()){
                direction = Direction.DOWN;
                return;
            }
            currentFloor++;
            if(!upList.isEmpty() && currentFloor == upList.get(0)){
                openDoor();
                upList.remove(0);
            }
            if(upList.isEmpty() && !downList.isEmpty()){
                direction = Direction.DOWN;
            }
        }
        else if(direction == Direction.DOWN){
            if(downList.isEmpty()){
                direction = Direction.UP;
                return;
            }
            currentFloor--;
            if(!downList.isEmpty() && currentFloor == downList.get(0)){
                openDoor();
                downList.remove(0);
            }
            if(downList.isEmpty() && !upList.isEmpty()){
                direction = Direction.UP;
            }
        }
    }



public static void main(String[] args) {
    elevator Elevator = new elevator(0, 10, 0);
    Scanner scanner = new Scanner(System.in);

    while(true){ // give some initial data
        System.out.println("Enter a floor request (0-10), enter nothing to skip, enter 'q' to quit:");
        String input = scanner.nextLine();
        if(input.isEmpty()){
            continue;
        }
        if(input.equals("q")){
            break;
        }
        int floor = Integer.parseInt(input);
        Elevator.requestFloor(floor);
    }
    while(true){
        Elevator.move();
        System.out.println("Current floor: " + Elevator.getFloor());
        System.out.println("Enter a floor request (0-10), enter nothing to skip, enter 'q' to quit:");
        String input = scanner.nextLine();
        if(input.isEmpty()){
            continue;
        }
        if(input.equals("q")){
            break;
        }
        int floor = Integer.parseInt(input);
        Elevator.requestFloor(floor);
    }

    scanner.close();

}

}







