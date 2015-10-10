package com.liuliume.portal.service;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.entity.Address;
import com.liuliume.portal.entity.Room;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface RoomService {
	
	/**
	 * 查询Address表
	 * @param seed
	 * @throws Exception
	 */
	public List<Room> list(Seed<Room> seed) throws Exception;

    public Room findRoomById(Integer room_id) throws Exception;

    public void createOrUpdate(Room room) throws Exception;

    public void batchDelete(String address_ids) throws Exception;
    
    public List<Room> listAllRooms() throws Exception;

    public boolean isRoomNotEmpty(String startDate,String endDate,Integer room_id);
}
