package com.hist.carMobile.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hist.carMobile.mapper.TestMapper;
import com.hist.carMobile.vo.CarMng;

/**
 * <B>Project Name : </B>car-mobile<br/>
 * <B>Package Name : </B>com.hist.carMobile.svc<br/>
 * <B>File Name : </B>TestService<br/>
 * <B>Description</B>
 * <ul>
 * <li>Database 연동 RestFul request 예제 service, transaction 처리
 * </ul>
 * 
 * @author Joungmin
 * @since 2019. 6. 12.
 */
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = { Exception.class,
		RuntimeException.class })
@Service
public class TestService {

	@Autowired
	TestMapper testMapper;

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
	public List<CarMng> getAllCarMng() throws Exception {
		return testMapper.getAllCarMng();
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
	public List<CarMng> getCarMng(String confNo) throws Exception {
		return testMapper.getCarMng(confNo);
	}

}