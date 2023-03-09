package com.ldr.manage.base.major;

import lombok.Getter;
import lombok.Setter;
//import lombok.Builder;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import com.ldr.common.base.annotation.Exclude;
import com.ldr.common.base.annotation.Id;
import com.ldr.common.base.annotation.Table;
import com.ldr.common.base.key.IdType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @类说明 【专业】实体
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
@Setter
@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Schema(name = "base_major", description = "【专业】实体")
@Table("base_major")
public class Major {
	// 字段↓
	@Id(IdType.SNOW)
	@Schema(name = "id", description = "主键id")
	private Long id; /* 主键id */
	@Schema(name = "name", description = "专业名称")
	private String name; /* 专业名称 */
	@Schema(name = "major_number", description = "专业编号")
	private String majorNumber; /* 专业编号 */
	@Schema(name = "institute_id", description = "学院id")
	private Long instituteId; /* 学院id */
	/* 扩展(显示)属性↓@Exclude */
}
