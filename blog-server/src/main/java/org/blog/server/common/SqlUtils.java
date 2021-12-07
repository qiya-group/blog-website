package org.blog.server.common;

import org.blog.server.dto.PageDTO;
import org.blog.server.error.SQLInjectException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlUtils {

    /**
     * 获取sql（主要是用于分页搜索）
     * @param page page对象
     * @return 拼接的SQL
     */
    public static String getSql(PageDTO page) throws SQLInjectException {
        // 组装页码
        String limitSizeSql = " limit " + (page.getIndex() - 1) * page.getSize() + ", " +  page.getSize();
        // 如果没有key的时候还是不要作排序处理
        String orderSql = "";
        String searchKeySql = "";
        // 如果searchKey
        if (page.getKey() != null) {
            searchKeySql = " where " + page.getKey() + " like '%" + page.getValue() + "%' ";
            orderSql =SqlUtils.getOrderSql(page);
        }
        // 关键词搜索(完整搜索条件)
        String sql = searchKeySql + limitSizeSql + orderSql;
        // sql安全校验，是否存在sql注入
        if (SqlUtils.containsSqlInjection(sql) ) {
            throw new SQLInjectException(Constant.SQL_INJECT_ERROR);
        }
        return sql;
    }

    /**
     * 获取排序的SQL
     * @param page 页面对象
     * @return 返回OrderSQL
     */
    private static String getOrderSql(PageDTO page) {
        String orderSql = "ORDER BY " + page.getKey();
        // 1 为升序列
        if (page.getOrder() == 1) {
            orderSql += "ASC ";
        } else {
            orderSql += "DESC ";
        }
        return orderSql;
    }

    /**
     * sql注入检查
     * @param sql 待检查的SQL
     * @return 返回匹配结果
     */
    public static boolean containsSqlInjection(String sql){
        Pattern pattern= Pattern.compile("\\b(and|exec|insert|select|drop|grant|alter|delete|update|count|chr|mid|master|truncate|char|declare|or)\\b|(\\*|;|\\+|'|%)");
        Matcher matcher=pattern.matcher(sql.toString().toLowerCase());
        return matcher.find();
    }
}
