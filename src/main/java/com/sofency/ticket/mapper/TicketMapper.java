package com.sofency.ticket.mapper;

import com.sofency.ticket.pojo.Ticket;
import com.sofency.ticket.pojo.TicketExample;
import com.sofency.ticket.pojo.TicketKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TicketMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    long countByExample(TicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    int deleteByExample(TicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    int deleteByPrimaryKey(TicketKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    int insert(Ticket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    int insertSelective(Ticket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    List<Ticket> selectByExampleWithRowbounds(TicketExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    List<Ticket> selectByExample(TicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    Ticket selectByPrimaryKey(TicketKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    int updateByExampleSelective(@Param("record") Ticket record, @Param("example") TicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    int updateByExample(@Param("record") Ticket record, @Param("example") TicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    int updateByPrimaryKeySelective(Ticket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbg.generated Thu Apr 16 09:24:31 CST 2020
     */
    int updateByPrimaryKey(Ticket record);
}