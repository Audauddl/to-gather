package com.greedy.togather.admin.board.model.dto;

import java.util.Date;

import lombok.Data;

@Data 
public class AdminBoardDTO {

private String boardNo;
private String boardType;
private String boardTitle;
private String boardContent;
private Date writeDate;
private String postStatus;
private Date modifyDate;
private String boardTitleHead;
private int views;
private String userNo;








}


