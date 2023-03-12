package com.jljcxy.manage.base.clazz;

import lombok.Getter;
import lombok.Setter;
//import lombok.Builder;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import com.ldr.common.base.annotation.Exclude;
import com.jljcxy.common.base.annotation.Id;
import com.jljcxy.common.base.annotation.Table;
import com.jljcxy.common.base.key.IdType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @类说明 【班级】实体
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
@Setter
@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Schema(name = "base_clazz", description = "【班级】实体")
@Table("base_clazz")
public class Clazz {
	// 字段↓
	@Id(IdType.SNOW)
	@Schema(name = "id", description = "主键id")
	private Long id; /* 主键id */
	@Schema(name = "name", description = "班级名称")
	private String name; /* 班级名称 */
	@Schema(name = "major_id", description = "专业id")
	private Long majorId; /* 专业id */
	@Schema(name = "institute_id", description = "学院id")
	private Long instituteId; /* 学院id */
	/* 扩展(显示)属性↓@Exclude */
}
