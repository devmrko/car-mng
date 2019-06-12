package com.hist.carMobile.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hist.carMobile.vo.CarMng;

/**
	 * <B>Project Name : </B>car-mobile<br/>
	 * <B>Package Name : </B>com.hist.carMobile.mapper<br/>
	 * <B>File Name : </B>TestMapper<br/>
	 * <B>Description</B>
	 * <ul> 
	 * <li>Database 연동 RestFul request 예제 mapper, (* resources/패키지 path/mapper명.xml 에 있는 패키지 명과 method 명이 동일해야 한다.)
	 * </ul>
	 * 
	 * @author Joungmin
	 * @since 2019. 6. 12.
	 */
@Repository
public interface TestMapper {

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
	public List<CarMng> getAllCarMng() throws Exception;

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
	public List<CarMng> getCarMng(String confNo) throws Exception;

}