package org.egov.fsm.calculator.web.models;

import java.util.List;

import org.egov.fsm.calculator.web.models.demand.TaxHeadEstimate;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class EstimatesAndSlabs {

	 @JsonProperty("estimates")
	    private List<TaxHeadEstimate> estimates;
}
