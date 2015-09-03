package com.liuliume.portal.mybatis.mapper;

import com.liuliume.portal.entity.Room;
import com.liuliume.portal.mybatis.MyBatisBaseMapper;
import com.liuliume.portal.mybatis.Parameter;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomMapper extends MyBatisBaseMapper<Room> {
    int deleteByPrimaryKey(Integer id);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    int count(@Param("param")Parameter parameter);

    public List<Room> list(@Param("param")Parameter parameter);
    
    public List<Room> listAllRooms() throws Exception;

}