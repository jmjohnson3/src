package model;

public class FreeRoom extends Room{
    public FreeRoom(String number, RoomType RoomType){
        super(number, 0.0, roomType);        
    }


    @Override
    public String toString(){
        return "Room Number: " + number + ", Room Price: " + price + ", Room Type: " + roomType;
    }
}
