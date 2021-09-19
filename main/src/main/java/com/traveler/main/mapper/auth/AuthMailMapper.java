package com.traveler.main.mapper.auth;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.traveler.main.vo.auth.CertifyKeyVo;

@Mapper
@Repository
public interface AuthMailMapper {

	public void saveCertifyKey(CertifyKeyVo certifyKeyVo) throws Exception;
	
//	public void updateCertifykey(CertifyKeyVo certifyKeyVo) throws Exception;
	
	public int validateTimeCertifyKey(CertifyKeyVo certifyKeyVo) throws Exception;
	
	public int validateCertifyKey(CertifyKeyVo certifyKeyVo) throws Exception;
	
}
