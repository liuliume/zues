package com.liuliume.portal.controller.hairdressing;

import com.liuliume.common.pagination.Seed;
import com.liuliume.common.web.spring.mvc.annotation.SeedParam;
import com.liuliume.portal.common.JData;
import com.liuliume.portal.entity.Account;
import com.liuliume.portal.entity.Hairdressing;
import com.liuliume.portal.entity.HairdressingTime;
import com.liuliume.portal.service.HairdressingService;
import com.liuliume.portal.service.HairdressingTimeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by clement on 8/29/15.
 */
@Controller
@RequestMapping(value = { "/hairdressing_time" }, method = RequestMethod.GET)
public class HairdressingTimeController {

	private Logger logger = LoggerFactory
			.getLogger(HairdressingTimeController.class);

	@Autowired
	private HairdressingTimeService hairdressingTimeService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	private ModelAndView list(ModelMap map,
			@SeedParam Seed<HairdressingTime> seed) {
		logger.info("call AddressController.list");
		try {
			hairdressingTimeService.list(seed);
			seed.setActionPath("hairdressing_time/list");
		} catch (Exception e) {
			logger.error(MessageFormat.format(
					"Get Account list error! reason:{0}, Paramter:seed:{1}.",
					e.getMessage(), seed.toString()), e);
		}
		ModelAndView mav = new ModelAndView(
				"hairdressing_time/list_hairdressingTime");
		// Seed<HashMap<String, Object>> resultSeed = new
		// Seed<HashMap<String,Object>>();
		// Method[] methods = Seed.class.getMethods();
		// for (Method method : methods){
		// if("get".equalsIgnoreCase(method.getName().substring(0,3))){
		// for(Method method1 : methods) {
		// if("set".equalsIgnoreCase(method1.getName().substring(0,3))
		// &&
		// method.getName().substring(3,method.getName().length()-1).equalsIgnoreCase(method1.getName().substring(3,method1.getName().length()-1))
		// &&
		// "result".equalsIgnoreCase(method1.getName().substring(3,method1.getName().length()-1)))
		// {
		// try {
		// method1.invoke(resultSeed,method.invoke(seed));
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// } catch (InvocationTargetException e) {
		// e.printStackTrace();
		// }
		// }
		// }
		// }
		// }
		// resultSeed.setResult(list);
		map.put("seed", seed);
		return mav;
	}

	@RequestMapping(value = "index", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView index(
			ModelMap model,
			@RequestParam(value = "hairdressingTime_id", required = false) Integer hairdressingTime_id) {
		HairdressingTime hairdressingTime = null;
		try {
			logger.info("call AddressController.index");
			hairdressingTime = hairdressingTimeService
					.findHairdressingTimeById(hairdressingTime_id);
		} catch (Exception e) {
			logger.error("Error! reason:{}, Paramter:account_id:{}.",
					e.getMessage(), hairdressingTime_id, e);
		}
		ModelAndView mav = new ModelAndView(
				"/hairdressing_time/index_hairdressingTime");
		if (hairdressingTime != null) {
			model.put("hairdressingTime", hairdressingTime);
		}
		return mav;
	}

	@RequestMapping(value = "createOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JData createOrUpdate(HairdressingTime hairdressingTime,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("call the createOrUpdate account");
		logger.debug(hairdressingTime.toString());
		JData jData = new JData();
		try {
			hairdressingTimeService.createOrUpdate(hairdressingTime);
			jData.setCode(200);
			jData.setSuccess(true);
			jData.setDetail("操作成功");
		} catch (Exception e) {
			logger.error("create Or Update  Error." + e.getMessage()
					+ " account[" + hairdressingTime + "]", e);
			jData.setCode(500);
			jData.setSuccess(false);
			jData.setDetail("操作失败");
		}
		return jData;
	}

	@RequestMapping(value = "batchDelete", method = RequestMethod.POST)
	@ResponseBody
	public JData batchDelete(
			@RequestParam(value = "hairdressingTimeIds", required = true) String hairdressingTimeIds) {
		logger.info("call the batch delete account");
		JData jdata = new JData();
		try {
			hairdressingTimeService.batchDelete(hairdressingTimeIds);
			jdata.setSuccess(true);
			jdata.setDetail("操作成功");
		} catch (Exception e) {
			logger.error("Batch delete[Account] Error." + e.getMessage()
					+ " accountIds[" + hairdressingTimeIds + "]", e);
			jdata.setDetail("Batch delete [Account] failed!");
			jdata.setSuccess(false);
		}
		return jdata;
	}

	@RequestMapping(value = "listValidHairingDressingTime")
	@ResponseBody
	public JData listValidHairingDressingTime(
			@RequestParam(value = "serviceDate", required = true) Date serviceDate) {
		JData jData = new JData("操作成功", true);
		try {
			logger.info("call listValidHairingDressingTime");
			List<HairdressingTime> list = hairdressingTimeService
					.listValidHairingDressingTime(serviceDate);
			jData.setCode(200);
			jData.setData(list);
		} catch (Exception e) {
			logger.error(
					"Error in listValidHairingDressingTime! reason:{}, Paramter:account_id:{}.",
					e.getMessage(), serviceDate, e);
			jData.setCode(500);
			jData.setData(null);
			jData.setSuccess(false);
			jData.setDetail("操作失败");
		}
		return jData;
	}
	
	@RequestMapping(value="isServiceTimeValid",method=RequestMethod.GET)
    @ResponseBody
    public JData isServiceTimeValid(String serviceTime,int service_type){
    	JData jData = new JData();
    	jData.setSuccess(true);
    	return jData;
    }
}
