package org.egov.fsm.web.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FSMSearchCriteria {
    @JsonProperty("offset")
    private Integer offset;

    @JsonProperty("limit")
    private Integer limit; 
    
    @JsonProperty("tenantId")
    private String tenantId;
    
    @JsonProperty("mobileNumber")
    private String mobileNumber; 
    
    @JsonProperty("applicationStatus")
    private String applicationStatus; 
    
    @JsonProperty("ownerIds")
    private List<String> ownerIds;
    
    @JsonProperty("fromDate")
    private Long fromDate; 
    
    @JsonProperty("toDate")
    private Long toDate;
    
    @JsonProperty("applicationNumber")
    private List<String> applicationNumber;
    
    @JsonProperty("ids")
    private List <String> ids;
    
    public boolean isEmpty() {
		// TODO Auto-generated method stub
		 return (this.tenantId == null && this.offset == null && this.limit == null && this.mobileNumber == null
				 && StringUtils.isEmpty(this.applicationStatus)  && this.ownerIds == null && this.fromDate == null && this.toDate == null && CollectionUtils.isEmpty(this.applicationNumber) && CollectionUtils.isEmpty(this.ids));
	}

	public boolean tenantIdOnly() {
		// TODO Auto-generated method stub
		return (this.tenantId != null && this.mobileNumber == null
                && StringUtils.isEmpty(this.applicationStatus) && this.ownerIds == null && this.fromDate == null && this.toDate == null && CollectionUtils.isEmpty(this.applicationNumber) && CollectionUtils.isEmpty(this.ids));
	} 
}
