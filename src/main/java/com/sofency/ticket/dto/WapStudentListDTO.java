package com.sofency.ticket.dto;

import lombok.Data;

import java.util.List;

/**
 * @author sofency
 * @date 2020/5/3 9:54
 * @package IntelliJ IDEA
 * @description
 */
@Data
public class WapStudentListDTO {
    private List<StudentDTO> studentDTOList;
    private ResultMsg resultMsg;
}
