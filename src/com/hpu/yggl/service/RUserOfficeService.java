package com.hpu.yggl.service;

import java.util.List;
import com.hpu.yggl.bean.RUserOffice;

public interface RUserOfficeService {

	List<RUserOffice> getRUOByUserId(String userId);

	void saveRUO(RUserOffice ruo);

	void deleteRUO(RUserOffice ruo);

	List<RUserOffice> getRUOByOfficeId(String officeId);

	void updateRUO(RUserOffice rUserOffice);
}
