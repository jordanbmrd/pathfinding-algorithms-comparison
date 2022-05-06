package sae.dungeon;

import java.util.HashMap;
import java.util.Map;

public class Room {

	private String name;
	private Map<Direction, Room> nextRooms;
	private Coord coords;

	public Room(String name, Coord coords) {
		super();
		this.coords = coords;
		this.name = name;
		nextRooms = new HashMap<>();
	}

	public void addRoomToDirection(Room otherRoom, Direction direction) {
		if (nextRooms.get(direction) != null)
			return;
		nextRooms.put(direction, otherRoom);
		otherRoom.addRoomToDirection(this, direction.oppositeDirection());
	}
	
	public Direction getDirection(Room otherRoom) {
		// Renvoie la direction de la chambre vers une autre chambre
		for (Map.Entry<Direction, Room> entry : this.nextRooms.entrySet()) {
			if (entry.getValue().equals(otherRoom)) return entry.getKey();
		}
		return null;
	}

	@Override
	public String toString() {
		return "Room " + name + " " + coords + " [adjacent rooms: "
				+ nextRooms.values().size() + "]";
	}

	public String getName() {
		return name;
	}

	public Coord getCoords() {
		return coords;
	}

	public Map<Direction, Room> getNextRooms() {
		return nextRooms;
	}
	
	@Override
	public boolean equals(Object obj) {
		Room otherRoom = (Room) obj;
		return this.getName() == otherRoom.getName() && this.getCoords() == otherRoom.getCoords();
	}

}
