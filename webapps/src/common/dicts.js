/**字典数据读取与翻译(格式化) */
import Vue from "vue";
import { DICTS } from "@/common/Constant";
import moment from "moment";
function getItemValue(typeCode, val) {
  let item = Vue.ls.get(DICTS)[typeCode].filter(item => item.value == val);
  if (item.length == 0) return val;
  return item[0].text;
}
export const getDict = (typeCode) => Vue.ls.get(DICTS)[typeCode];//按字典类取字典数据

export const genderFormat = (row, column) => getItemValue('gender', row[column.property]);//性别
export const statusFormat = (row, column) => getItemValue('status', row[column.property]);//状态
export const deptTypeFormat = (row, column) => getItemValue('deptType', row[column.property]);//机构类型
export const requestTypeFormat = (row, column) => getItemValue('requestType', row[column.property]);//请求类型
export const pesonTypeFormat = (row, column) => getItemValue('personType', row[column.property]);//人员类型
export const roleTypeFormat = (row, column) => getItemValue('roleType', row[column.property]);//角色类型
export const boxStatusFormat = (row, column) => getItemValue('boxStatus', row[column.property]);//钥匙柜状态
export const gridStatusFormat = (row, column) => getItemValue('gridStatus', row[column.property]);//物品柜格子状态
export const cabinetStatusFormat = (row, column) => getItemValue('cabinetStatus', row[column.property]);//物品柜状态
export const goodsBoardStatusFormat = (row, column) => getItemValue('goodsBoardStatus', row[column.property]);//物品柜通讯板状态
export const logTypeFormat = (row, column) => getItemValue('logType', row[column.property]);//日志类型
export const alertTypeFormat = (row, column) => getItemValue('alertType', row[column.property]);//告警类型
export const firmTypeFormat = (row, column) => getItemValue('firmType', row[column.property]);//厂家类型
export const boxGroupFormat = (row, column) => getItemValue('boxGroup', row[column.property]);//组信息
export const protocolTypeFormat = (row, column) => getItemValue('protocolType', row[column.property]);//协议类型
export const planTypeFormat = (row, column) => getItemValue('planType', row[column.property]);//计划类型
export const PlanStatusFormat = (row, column) => getItemValue('planStatus', row[column.property]);//计划类型
export const FuncTypeFormat = (row, column) => getItemValue('funcType', row[column.property]);//功能类型
export const operationTypeFormat = (row, column) => getItemValue('operationType', row[column.property]);//操作类型
export const itemTypeFormat = (row, column) => getItemValue('itemType', row[column.property]);//物品类型
export const isDoorFormat = (row, column) => getItemValue('isDoor', row[column.property]);//钥匙柜通讯板是否控制开门
export const keyStatusFormat = (row, column) => getItemValue('keyStatus', row[column.property]);//钥匙状态
export const keyReturnStatusFormat = (row, column) => getItemValue('keyReturnStatus', row[column.property]);//钥匙归还状态
export const courseTypeFormat = (row, column) => getItemValue('courseType', row[column.property]);//课程类型
export const examTypeFormat = (row, column) => getItemValue('examType', row[column.property]);//考试类型
export const dateFormat = (row, column) => { //时间格式化
  var date = row[column.property];
  if (date == undefined) return "";
  return moment(date).format("YYYY-MM-DD HH:mm:ss");
};