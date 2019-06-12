package com.hist.carMobile.ctr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hist.carMobile.svc.TestService;
import com.hist.carMobile.vo.CarMng;

/**
 * <B>Project Name : </B>car-mobile<br/>
 * <B>Package Name : </B>com.hist.carMobile.ctr<br/>
 * <B>File Name : </B>TestController<br/>
 * <B>Description</B>
 * <ul>
 * <li>Database 연동 RestFul request 예제
 * </ul>
 * 
 * @author Joungmin
 * @since 2019. 6. 12.
 */
@RestController
@RequestMapping("/car")
public class TestController {

	@Autowired
	TestService testService;

	/**
	 * <B>History</B>
	 * <ul>
	 * <li>Date : 2019. 6. 12.
	 * <li>Developer : Joungmin
	 * <li>car_mng table 전체 결과 리턴
	 * </ul>
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAll")
	public @ResponseBody List<CarMng> getAllCarMng() throws Exception {
		return testService.getAllCarMng();
	}

	/**
	 * <B>History</B>
	 * <ul>
	 * <li>Date : 2019. 6. 12.
	 * <li>Developer : Joungmin
	 * <li>car_mng table 중 conf_no에 해당하는 결과 리턴
	 * </ul>
	 * 
	 * @param confNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/get")
	public @ResponseBody List<CarMng> getCarMng(@RequestParam String confNo) throws Exception {
		return testService.getCarMng(confNo);
	}

}