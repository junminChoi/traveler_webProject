package com.traveler.main.service.auth;

import com.traveler.main.vo.auth.CertifyKeyVo;

public interface AuthMailService {

	public boolean sendMail(String userEmail) throws Exception;
	
//	public boolean resendMail(String userEmail) throws Exception;
	
	public boolean validateTimeCertifyKey(CertifyKeyVo certifyKeyVo) throws Exception;
	
	public boolean validateCertifyKey(CertifyKeyVo certifyKeyVo) throws Exception;
}
