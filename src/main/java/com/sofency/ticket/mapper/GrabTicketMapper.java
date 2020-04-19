package com.sofency.ticket.mapper;

import com.sofency.ticket.pojo.GrabTicket;
import com.sofency.ticket.pojo.GrabTicketExample;
import com.sofency.ticket.pojo.GrabTicketKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GrabTicketMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    long countByExample(GrabTicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    int deleteByExample(GrabTicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    int deleteByPrimaryKey(GrabTicketKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    int insert(GrabTicket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    int insertSelective(GrabTicket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    List<GrabTicket> selectByExampleWithRowbounds(GrabTicketExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    List<GrabTicket> selectByExample(GrabTicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    GrabTicket selectByPrimaryKey(GrabTicketKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    int updateByExampleSelective(@Param("record") GrabTicket record, @Param("example") GrabTicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    int updateByExample(@Param("record") GrabTicket record, @Param("example") GrabTicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    int updateByPrimaryKeySelective(GrabTicket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grabticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    int updateByPrimaryKey(GrabTicket record);
}