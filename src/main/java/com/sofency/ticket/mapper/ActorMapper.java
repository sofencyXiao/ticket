package com.sofency.ticket.mapper;

import com.sofency.ticket.pojo.Actor;
import com.sofency.ticket.pojo.ActorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ActorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbg.generated Wed Apr 29 18:29:03 CST 2020
     */
    long countByExample(ActorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbg.generated Wed Apr 29 18:29:03 CST 2020
     */
    int deleteByExample(ActorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbg.generated Wed Apr 29 18:29:03 CST 2020
     */
    int deleteByPrimaryKey(Integer actorId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbg.generated Wed Apr 29 18:29:03 CST 2020
     */
    int insert(Actor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbg.generated Wed Apr 29 18:29:03 CST 2020
     */
    int insertSelective(Actor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbg.generated Wed Apr 29 18:29:03 CST 2020
     */
    List<Actor> selectByExampleWithRowbounds(ActorExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbg.generated Wed Apr 29 18:29:03 CST 2020
     */
    List<Actor> selectByExample(ActorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbg.generated Wed Apr 29 18:29:03 CST 2020
     */
    Actor selectByPrimaryKey(Integer actorId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbg.generated Wed Apr 29 18:29:03 CST 2020
     */
    int updateByExampleSelective(@Param("record") Actor record, @Param("example") ActorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbg.generated Wed Apr 29 18:29:03 CST 2020
     */
    int updateByExample(@Param("record") Actor record, @Param("example") ActorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbg.generated Wed Apr 29 18:29:03 CST 2020
     */
    int updateByPrimaryKeySelective(Actor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbg.generated Wed Apr 29 18:29:03 CST 2020
     */
    int updateByPrimaryKey(Actor record);
}