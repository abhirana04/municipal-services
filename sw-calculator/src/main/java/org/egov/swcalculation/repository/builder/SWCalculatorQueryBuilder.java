package org.egov.swcalculation.repository.builder;

import java.util.List;

import org.egov.swcalculation.web.models.BillGenerationSearchCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

@Controller
public class SWCalculatorQueryBuilder {
	
	private static final String connectionNoListQuery = "SELECT distinct(conn.connectionno),sw.connectionexecutiondate FROM eg_sw_connection conn INNER JOIN eg_sw_service sw ON conn.id = sw.connection_id";
	
	private static final String distinctTenantIdsCriteria = "SELECT distinct(tenantid) FROM eg_sw_connection sw";

	private static final String billGenerationSchedulerSearchQuery = "SELECT * from eg_sw_scheduler ";

	private static final String BILL_SCHEDULER_STATUS_UPDATE_QUERY = "UPDATE eg_ws_scheduler SET status=? where id=?";

	private static final String connectionNoByLocality = "SELECT distinct(conn.connectionno) FROM eg_sw_connection conn INNER JOIN eg_sw_service ws ON conn.id = ws.connection_id";

	public String getDistinctTenantIds() {
		return distinctTenantIdsCriteria;
	}

	public String getConnectionNumberList(String tenantId, String connectionType, String status, List<Object> preparedStatement) {
		StringBuilder query = new StringBuilder(connectionNoListQuery);
		// Add connection type
		addClauseIfRequired(preparedStatement, query);
		query.append(" sw.connectiontype = ? ");
		preparedStatement.add(connectionType);
		
		//Add status
		addClauseIfRequired(preparedStatement, query);
		query.append(" conn.status = ? ");
		preparedStatement.add(status);
		

		// add tenantid
		addClauseIfRequired(preparedStatement, query);
		query.append(" conn.tenantid = ? ");
		preparedStatement.add(tenantId);
		
		//Add not null condition
		addClauseIfRequired(preparedStatement, query);
		query.append(" conn.connectionno is not null");
		return query.toString();
	}

	private void addClauseIfRequired(List<Object> values, StringBuilder queryString) {
		if (values.isEmpty())
			queryString.append(" WHERE ");
		else {
			queryString.append(" AND ");
		}
	}
	
	public String getBillGenerationSchedulerQuery(BillGenerationSearchCriteria criteria,
			List<Object> preparedStatement) {
		StringBuilder query = new StringBuilder(billGenerationSchedulerSearchQuery);
		if (!StringUtils.isEmpty(criteria.getTenantId())) {
			addClauseIfRequired(preparedStatement, query);
			query.append(" tenantid= ? ");
			preparedStatement.add(criteria.getTenantId());
		}
		if (criteria.getLocality() != null) {
			addClauseIfRequired(preparedStatement, query);
			query.append(" locality = ? ");
			preparedStatement.add(criteria.getLocality());
		}
		if (criteria.getStatus() != null) {
			addClauseIfRequired(preparedStatement, query);
			query.append(" status = ? ");
			preparedStatement.add(criteria.getStatus());
		}
		if (criteria.getBillingcycleStartdate() != null) {
			addClauseIfRequired(preparedStatement, query);
			query.append(" billingcyclestartdate >= ? ");
			preparedStatement.add(criteria.getBillingcycleStartdate());
		}
		if (criteria.getBillingcycleEnddate() != null) {
			addClauseIfRequired(preparedStatement, query);
			query.append(" billingcycleenddate <= ? ");
			preparedStatement.add(criteria.getBillingcycleEnddate());
		}

		return query.toString();
	}
	
	/**
	 * Bill expire query builder
	 * 
	 * @param billIds
	 * @param preparedStmtList
	 */
	public String getBillSchedulerUpdateQuery(String schedulerId, List<Object> preparedStmtList) {

		StringBuilder builder = new StringBuilder(BILL_SCHEDULER_STATUS_UPDATE_QUERY);

		return builder.toString();
	}

	public String getConnectionsNoByLocality(String tenantId, String connectionType,String status,String locality, List<Object> preparedStatement) {
		StringBuilder query = new StringBuilder(connectionNoByLocality);
		// Add connection type
		addClauseIfRequired(preparedStatement, query);
		query.append(" ws.connectiontype = ? ");
		preparedStatement.add(connectionType);

		//Active status	
		addClauseIfRequired(preparedStatement, query);
		query.append(" conn.status = ? ");
		preparedStatement.add(status);

		// add tenantid
		addClauseIfRequired(preparedStatement, query);
		query.append(" conn.tenantid = ? ");
		preparedStatement.add(tenantId);

//		addClauseIfRequired(preparedStatement, query);
//		query.append(" conn.connectionno = ? ");
//		preparedStatement.add("SW/107/2020-21/000018");

		if (locality != null) {
			addClauseIfRequired(preparedStatement, query);
			query.append(" locality = ? ");
			preparedStatement.add(locality);
		}

		addClauseIfRequired(preparedStatement, query);
		query.append(" conn.connectionno is not null");
		return query.toString();

	}
}
