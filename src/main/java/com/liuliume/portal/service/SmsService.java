package com.liuliume.portal.service;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.entity.Room;

import java.util.Date;
import java.util.List;

public interface SmsService {

    public boolean getMsgCode(String mobile) throws Exception;
}
