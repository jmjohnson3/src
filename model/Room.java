package model;

import java.util.Objects;

public class Room implements IRoom{

    public String number;
    protected Double price;
    protected RoomType roomType;

    public Room(String roomNumber, double price, RoomType roomType) {
        this.number = roomNumber;
        this.price = price;
        this.roomType = roomType;        
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(number, room.number) && Objects.equals(price, room.price) && Objects.equals(roomType, room.roomType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, price, roomType);
    }
}
