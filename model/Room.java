package model;

public class Room implements IRoom{

    protected String number;
    protected Double price;
    protected static RoomType roomType;




    public Room(String roomNumber, double price, RoomType roomType) {
        this.number = roomNumber;
        this.price = price;
        Room.roomType = roomType;
    }

    @Override
    public String getNumbeString() {
        // TODO Auto-generated method stub
        return number;
    }

    @Override
    public Double getPrice() {
        // TODO Auto-generated method stub
        return price;
    }

    @Override
    public RoomType getType() {
        // TODO Auto-generated method stub
        return roomType;
    }

    @Override
    public boolean isFree() {
        // TODO Auto-generated method stub
        return false;
    }
    

    @Override
    public String toString(){
        return "Room Number: " + number + ", Room Price: " + price + ", Room Type: " + roomType;
    }

    @Override
    public int hashCode() {
        return 0;
    }
        
    @Override
    public boolean equals(Object o) {
        return false;

    }
}
