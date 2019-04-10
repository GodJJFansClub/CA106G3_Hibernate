package com.foodOrder.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class FoodOrderCompQuery {
	
	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("food_or_status".equals(columnName)) // 用於其他
			aCondition = columnName + "='" + value + "'";
		else if ("cust_name".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("food_or_start".equals(columnName))                          // 用於Oracle的date
			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
		return aCondition + " ";
	}
	
	public static String getWhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
		
	}
	
	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("food_or_status", new String[] { "o1" });
		map.put("cust_name", new String[] { "cust" });
		map.put("food_or_start", new String[] { "2019-03-22" });

		String finalSQL = "select * from FOOD_ORDER FO JOIN CUST C ON FO.CUST_ID = C.CUST_ID "
				          + FoodOrderCompQuery.getWhereCondition(map)
				          + "order by FOOD_OR_ID DESC";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
