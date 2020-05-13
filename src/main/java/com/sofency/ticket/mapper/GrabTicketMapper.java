package com.sofency.ticket.mapper;

import com.sofency.ticket.pojo.GrabTicket;
import com.sofency.ticket.pojo.GrabTicketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GrabTicketMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Wed Apr 29 19:44:01 CST 2020
     */
    long countByExample(GrabTicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Wed Apr 29 19:44:01 CST 2020
     */
    int deleteByExample(GrabTicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Wed Apr 29 19:44:01 CST 2020
     */
    int deleteByPrimaryKey(Integer grapId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Wed Apr 29 19:44:01 CST 2020
     */
    int insert(GrabTicket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Wed Apr 29 19:44:01 CST 2020
     */
    int insertSelective(GrabTicket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Wed Apr 29 19:44:01 CST 2020
     */
    List<GrabTicket> selectByExampleWithRowbounds(GrabTicketExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Wed Apr 29 19:44:01 CST 2020
     */
    List<GrabTicket> selectByExample(GrabTicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Wed Apr 29 19:44:01 CST 2020
     */
    GrabTicket selectByPrimaryKey(Integer grapId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Wed Apr 29 19:44:01 CST 2020
     */
    int updateByExampleSelective(@Param("record") GrabTicket record, @Param("example") GrabTicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Wed Apr 29 19:44:01 CST 2020
     */
    int updateByExample(@Param("record") GrabTicket record, @Param("example") GrabTicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Wed Apr 29 19:44:01 CST 2020
     */
    int updateByPrimaryKeySelective(GrabTicket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Wed Apr 29 19:44:01 CST 2020
     */
    int updateByPrimaryKey(GrabTicket record);

    /**
     * 票数加1 退票功能
     */
    int addTicket(int grabId);

    /**
     * 票数减1
     * @param grapId
     * @return
     */
    int decrTicket(int grapId);
}