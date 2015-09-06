package com.liuliume.portal.service.impl;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.common.MBox;
import com.liuliume.portal.dao.RoomDao;
import com.liuliume.portal.dao.cond.RoomQueryCond;
import com.liuliume.portal.entity.Room;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.service.CountService;
import com.liuliume.portal.service.RoomService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountServiceImpl implements CountService {

	private Logger logger = LoggerFactory.getLogger(CountServiceImpl.class);

    @Autowired
    private RoomDao roomDao;

    public void roomCountMoney(Integer room_id){
        Room room = null;
        if(StringUtils.isNotBlank(String.valueOf(room_id))) {
            room = roomDao.findRoomById(room_id);
        }
    }

}
