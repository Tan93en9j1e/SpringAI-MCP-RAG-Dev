package com.tang.enums;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.enums
 * ClassName: ListSortEnum
 * Author: tmj
 * Date: 2026/7/1 15:35
 * version: 1.0
 * Description:
 */
public enum ListSortEnum {
    ASC("asc", "升序"),
    DESC("desc", "降序");

    public  final String value;
    public  final String type;

    ListSortEnum( String type,String value) {
        this.value = value;
        this.type = type;
    }
}
