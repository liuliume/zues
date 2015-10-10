package com.liuliume.portal.service.impl;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.common.MBox;
import com.liuliume.portal.dao.AddressDao;
import com.liuliume.portal.dao.OrdersDao;
import com.liuliume.portal.dao.RoomDao;
import com.liuliume.portal.dao.cond.AddressQueryCond;
import com.liuliume.portal.dao.cond.RoomQueryCond;
import com.liuliume.portal.entity.Address;
import com.liuliume.portal.entity.Room;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.service.AddressService;
import com.liuliume.portal.service.RoomService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RoomServiceImpl implements RoomService {

	private Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);
	
	@Autowired
	private RoomDao roomDao;

    @Autowired
    private OrdersDao ordersDao;

	@Override
	public List<Room> list(Seed<Room> seed) throws Exception{
		logger.info("Address Service list bengins.");
		List<Room> result = new ArrayList<Room>();
		
		Parameter parameter = MBox.convertParameter(seed);
		
		RoomQueryCond cond = new RoomQueryCond();
        Room room = new Room();

		if(seed.getFilter().containsKey("name")){
			String name = seed.getFilter().get("name");
            if(!StringUtils.isEmpty(name))
			    room.setRoomName(name);
		}
		//add other query condition here
		
		cond.setRoom(room);
		parameter.setCond(cond);
		
		int count = roomDao.count(parameter);
		seed.setTotalSize(count);
		if(count>0){
			result = roomDao.list(parameter);
			seed.setResult(result);
		}
		return result;
	}

    @Override
    public Room findRoomById(Integer room_id) throws Exception {
        Room room = null;
        if(room_id!=null)
            room = roomDao.findRoomById(room_id);
        return room;
    }

    @Override
    public void createOrUpdate(Room room) throws Exception {
        if(room==null)
            throw new IllegalArgumentException("Room为空");
        if(null == room.getId()){//add
            roomDao.createRoom(room);
        }else {
            roomDao.updateRoom(room);
        }
    }

    @Override
    @Transactional
    public void batchDelete(String room_ids) throws Exception {
        String[] aids = room_ids.split(",");
        for (String aid : aids) {
            Room room= new Room();
            room.setId(new Integer(aid));
            roomDao.delete(room);
        }
    }

	@Override
	public List<Room> listAllRooms() throws Exception {
		return roomDao.listAllRooms();
	}

    public boolean isRoomNotEmpty(String startDate,String endDate,Integer room_id) {
        Room room = roomDao.findRoomById(room_id);
        int room_num = room.getRoomNum();
        int order_num = ordersDao.countRoomOrders(startDate,endDate);
        if(room_num > order_num) {
            return true;
        } else{
            return false;
        }
    }

}
