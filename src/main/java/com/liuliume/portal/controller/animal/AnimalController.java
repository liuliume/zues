package com.liuliume.portal.controller.animal;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuliume.common.pagination.Seed;
import com.liuliume.common.web.spring.mvc.annotation.SeedParam;
import com.liuliume.portal.common.JData;
import com.liuliume.portal.entity.AnimalsType;
import com.liuliume.portal.service.AnimalService;

@Controller
@RequestMapping(value = { "animal" }, method = RequestMethod.GET)
public class AnimalController {

	private Logger logger = LoggerFactory.getLogger(AnimalController.class);

	@Autowired
	private AnimalService animalService;

	@RequestMapping(value = "typeList", method = RequestMethod.GET)
	public String listAnimalType(ModelMap map, @SeedParam Seed<AnimalsType> seed) {
		logger.info("call AnimalController.listAnimalType begin,param:{}", seed);
		try {
			animalService.list(seed);
			seed.setActionPath("animal/typeList");
		} catch (Exception e) {
			logger.error(
					MessageFormat
							.format("Get AnimalsType list error! reason:{0}, Paramter:seed:{1}.",
									e.getMessage(), seed.toString()), e);
		}
		map.put("seed", seed);
		return "animal/typeList";
	}
	
	@RequestMapping(value="typeIndex",method=RequestMethod.GET)
	public String typeIndex(ModelMap model,@RequestParam(value="id",required=false)Integer id){
		AnimalsType type =null;
		logger.info("call AnimalController.typeIndex");
		try {
			type=animalService.findAnimalsTypeById(id);
		} catch (Exception e) {
			logger.error(MessageFormat.format(
					"typeIndex AnimalsType error! reason:{0}, Paramter:ids:{1}.",
					e.getMessage(), id), e);
		}
		if(type!=null){
			model.put("type", type);
		}
		return "animal/typeIndex";
	}

	@RequestMapping(value = "batchDeleteAnimalType", method = RequestMethod.POST)
	@ResponseBody
	public JData batchDeleteAnimalType(
			@RequestParam(value = "ids", required = true) String ids) {
		logger.info("call the batch delete animalType");
		JData jdata = new JData();
		try {
			animalService.batchDeleteAnimalType(ids);
			jdata.setSuccess(true);
			jdata.setDetail("删除成功");
		} catch (Exception e) {
			logger.error(MessageFormat.format(
					"delete AnimalsType error! reason:{0}, Paramter:ids:{1}.",
					e.getMessage(), ids), e);
			jdata.setSuccess(false);
			jdata.setDetail("删除失败");
		}

		return jdata;
	}

	@RequestMapping(value = "createOrUpdateType", method = RequestMethod.POST)
	@ResponseBody
	public JData createOrUpdateType(AnimalsType type) {
		JData jData = new JData("操作成功", true);
		try {
			animalService.createOrUpdateType(type);
		} catch (Exception e) {
			logger.error(
					MessageFormat
							.format("createOrUpdate AnimalsType error! reason:{0}, Paramter:AnimalsType:{1}.",
									e.getMessage(), type), e);
			jData.setSuccess(false);
			jData.setDetail("操作失败");
		}
		return jData;
	}
}
