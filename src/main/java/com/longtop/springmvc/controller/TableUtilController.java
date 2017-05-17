package com.longtop.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longtop.springmvc.vo.FbVO;
import com.longtop.springmvc.vo.FieldCfgVO;
import com.longtop.springmvc.vo.TableCfgVO;
import com.longtop.springmvc.vo.TestVo;

@Controller
public class TableUtilController {

	
	@RequestMapping(value="createTable2", method=RequestMethod.POST)
	@ResponseBody
	private String createTableSql2(@RequestBody TestVo test){
		List<FbVO> list = test.getList();
		for(FbVO vo: list){
			System.out.println("a:"+vo.getA()+"   b:"+vo.getB()+"  c:"+vo.getC());
		}
		
		System.out.println(test.getAge()+"A1111111111");
		
		return "hello";
	}
	
	@RequestMapping(value="createTable", method=RequestMethod.POST)
	@ResponseBody
	private String createTableSql(@RequestBody TableCfgVO tableCfgVO){
		List<FieldCfgVO> list = tableCfgVO.getList();
		for(FieldCfgVO vo: list){
			System.out.println("isKey:"+vo.getIsKey()+"   type:"+vo.getType()+"  isNull:"+vo.getIsNull()+"  comment:"+vo.getComment());
		}
		
		System.out.println(tableCfgVO.getTableName());
		
		return "hello";
	}
	
	@RequestMapping("register")
	private String createTableSql(){
		System.out.println("Ìø×ªÒ³Ãæ");
		return "WEB-INF/jsp/success.jsp";
	}
}
