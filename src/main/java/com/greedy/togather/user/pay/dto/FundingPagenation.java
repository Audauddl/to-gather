package com.greedy.togather.user.pay.dto;

import java.util.HashMap;
import java.util.Map;

public class FundingPagenation {
	
	//FundingSelectCriteria 클래스 이름 
	public static FundingSelectCriteria getSelectCriteria(int page, 
			int totalCount, int limit, int buttonAmount, Map<String, String> searchMap) {
		
		int fundMaxPage; 				//전체 페이지에서 가장 마지막 페이지
		int fundStartPage;				//한번에 표시될 페이지 버튼의 시작할 페이지
		int fundEndPage;				//한번에 표시될 페이지 버튼의 시작할 페이지
		int fundStartRow;				//시작
		int fundEndRow;					//끝
		
		/* 총 페이지수 계산 */
		fundMaxPage = (int) Math.ceil((double) totalCount / limit);
		
		/* 현재 페이지에 보여줄 시작 페이지 수 */
		fundStartPage = (int) (Math.ceil((double) page / buttonAmount) -1) * buttonAmount + 1;
		
		/* 목록 아래쪽에 보여질 마지막 페이지 수 */
		fundEndPage = fundStartPage + buttonAmount -1;
		
		/* max 페이지가 더 작은 경우 마지막 페이지가 max 페이지이다. */
		if(fundMaxPage < fundEndPage) {
			fundEndPage = fundMaxPage;
		}
		
		/* 마지막 페이지는 0이 될 수 없기 때문에 게시물이 아무 것도 존재하지 않으면 max페이지와 endPage는 0이 된다. */
		if(fundMaxPage == 0 && fundEndPage == 0) {
			fundMaxPage = fundStartPage;
			fundEndPage = fundStartPage;
		}
		
		/* 조회 할 시작 번호와 마지막 행 번호를 계산 */
		fundStartRow = (page -1 ) * limit +1;
		fundEndRow = fundStartRow + limit -1;
		
		FundingSelectCriteria fundingSelectCriteria = new FundingSelectCriteria(page, totalCount, limit, buttonAmount, fundMaxPage, fundStartPage, fundEndPage, fundStartRow, fundEndRow, searchMap.get("searchCondition"), searchMap.get("searchValue"));
		return fundingSelectCriteria;
	}
	
	/* 검색어가 존재하지 않는 경우 */
	public static FundingSelectCriteria getSelectCriteria(int page, int totalCount, int limit, int buttonAmount) {
		
		return getSelectCriteria(page, totalCount, limit, buttonAmount, new HashMap<>());
	}
		
}
