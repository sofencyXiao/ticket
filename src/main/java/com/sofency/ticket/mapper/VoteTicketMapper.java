package com.sofency.ticket.mapper;

import com.sofency.ticket.pojo.VoteTicket;
import com.sofency.ticket.pojo.VoteTicketExample;
import com.sofency.ticket.pojo.VoteTicketKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface VoteTicketMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voteticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    long countByExample(VoteTicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voteticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    int deleteByExample(VoteTicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voteticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    int deleteByPrimaryKey(VoteTicketKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voteticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    int insert(VoteTicket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voteticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    int insertSelective(VoteTicket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voteticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    List<VoteTicket> selectByExampleWithRowbounds(VoteTicketExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voteticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    List<VoteTicket> selectByExample(VoteTicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voteticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    VoteTicket selectByPrimaryKey(VoteTicketKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voteticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    int updateByExampleSelective(@Param("record") VoteTicket record, @Param("example") VoteTicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voteticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    int updateByExample(@Param("record") VoteTicket record, @Param("example") VoteTicketExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voteticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    int updateByPrimaryKeySelective(VoteTicket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voteticket
     *
     * @mbg.generated Sun Apr 19 08:40:26 CST 2020
     */
    int updateByPrimaryKey(VoteTicket record);
}