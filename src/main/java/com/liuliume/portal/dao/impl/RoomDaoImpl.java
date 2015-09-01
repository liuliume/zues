package com.liuliume.portal.dao.impl;

import com.liuliume.portal.dao.RoomDao;
import com.liuliume.portal.entity.Room;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.mybatis.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class RoomDaoImpl implements RoomDao{

	@Autowired
	private RoomMapper roomMapper;
	
	@Override
	public int count(Parameter parameter) {
		return roomMapper.count(parameter);
	}

	@Override
	public List<Room> list(Parameter parameter) {
		return roomMapper.list(parameter);
	}

    @Override
    public Room findRoomById(Integer room_id) {
        return roomMapper.selectByPrimaryKey(room_id);
    }


    @Override
    public void createRoom(Room room) {
        roomMapper.createEntity(room);
    }

    @Override
    public void updateRoom(Room room) {
        roomMapper.updateEntity(room);
    }

    @Override
    public void delete(Room room) {
        roomMapper.deleteEntityPhysically(room);
    }


}
