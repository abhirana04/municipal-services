package org.egov.swservice.service;

import org.egov.swservice.model.SewerageConnectionRequest;
import org.egov.swservice.model.ValidatorResult;
import org.egov.swservice.util.SWConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Component
public class SewerageFieldValidator implements SewerageActionValidator {

	@Override
	public ValidatorResult validate(SewerageConnectionRequest sewerageConnectionRequest, int reqType) {
		Map<String, String> errorMap = new HashMap<>();
		switch (reqType){
			case SWConstants.UPDATE_APPLICATION:
				validateUpdateRequest(sewerageConnectionRequest, errorMap);
			    break;
			case SWConstants.MODIFY_CONNECTION:
				validateModifyRequest(sewerageConnectionRequest, errorMap);
			default:
				break;
		}
		if (!errorMap.isEmpty())
			return new ValidatorResult(false, errorMap);
		return new ValidatorResult(true, errorMap);
	}

	public void validateUpdateRequest(SewerageConnectionRequest sewerageConnectionRequest, Map<String, String> errorMap) {
		if (SWConstants.ACTIVATE_CONNECTION_CONST.equalsIgnoreCase(
				sewerageConnectionRequest.getSewerageConnection().getProcessInstance().getAction())) {
			if (StringUtils.isEmpty(sewerageConnectionRequest.getSewerageConnection().getConnectionType())) {
				errorMap.put("INVALID_SEWERAGE_CONNECTION_TYPE", "Connection type should not be empty");
			}
			if (StringUtils
					.isEmpty(sewerageConnectionRequest.getSewerageConnection().getConnectionExecutionDate())) {
				errorMap.put("INVALID_CONNECTION_EXECUTION_DATE", "Connection execution date should not be empty");
			}
		}
		if (SWConstants.APPROVE_CONNECTION_CONST.equalsIgnoreCase(
				sewerageConnectionRequest.getSewerageConnection().getProcessInstance().getAction())) {
			if (StringUtils.isEmpty(sewerageConnectionRequest.getSewerageConnection().getRoadType())) {
				errorMap.put("INVALID_ROAD_TYPE", "Road type should not be empty");
			}
			if (StringUtils.isEmpty(sewerageConnectionRequest.getSewerageConnection().getRoadCuttingArea())) {
				errorMap.put("INVALID_ROAD_CUTTING_AREA", "Road cutting area should not be empty");
			}
		}
	}

	public void validateModifyRequest(SewerageConnectionRequest sewerageConnectionRequest, Map<String, String> errorMap) {
		if (SWConstants.APPROVE_CONNECTION.equalsIgnoreCase(
				sewerageConnectionRequest.getSewerageConnection().getProcessInstance().getAction())) {
			if (StringUtils.isEmpty(sewerageConnectionRequest.getSewerageConnection().getConnectionType())) {
				errorMap.put("INVALID_SEWERAGE_CONNECTION_TYPE", "Connection type should not be empty");
			}
			if (StringUtils
					.isEmpty(sewerageConnectionRequest.getSewerageConnection().getConnectionExecutionDate())) {
				errorMap.put("INVALID_CONNECTION_EXECUTION_DATE", "Connection execution date should not be empty");
			}
		}
	}
}
