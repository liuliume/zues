package com.liuliume.portal.dao.cond;

import com.liuliume.portal.entity.Room;
import com.liuliume.portal.mybatis.QueryCond;

import java.util.Map;

public class RoomQueryCond implements QueryCond{
	
	private Room room;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
}
