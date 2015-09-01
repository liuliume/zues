package com.liuliume.portal.dao;

import com.liuliume.portal.entity.Address;
import com.liuliume.portal.entity.Room;
import com.liuliume.portal.mybatis.Parameter;

import java.util.HashMap;
import java.util.List;

public interface RoomDao {
	
	public int count(Parameter parameter);
	
	public List<Room> list(Parameter parameter);

    public Room findRoomById(Integer room_id);

    public void createRoom(Room room);

    public void updateRoom(Room room);

    public void delete(Room room);

}
