/**列表页混入方法,不要增加任何与业务有关的代码,如果有相关业务代码,请将在当前列表页重写对应方法即可 */
import { dateFormat } from "@/common/dicts";
export const pageMix = {
  data() {
    return {
      total: 0, /*记录个数*/ 
      page: 1, /*当前页*/ 
      size: 20, /*页大小*/ 
      dataList: [], /*数据列表*/ 
      loading: false, /*是否加载列表*/ 
      selectIds: [], /*选中的记录主键*/ 
      form: this.initForm(),/*初始化列表页查询条件参数*/
      allShow:false,/*显示全部条件 */
    }
  },
  methods: {
    /*格式化日期*/
    dateFormat,
    /*分页--切换页码,执行查询*/
    pageChange(page) { this.page = page; this.loadData(); },
    /*分页--改变页大小,执行查询*/
    sizeChange(size) { this.size = size; this.loadData(); },
    /*清空查询条件,执行查询*/
    doReset() { this.form = this.initForm(); this.loadData(); },
    /*选中记录*/
    selectChange(rows, id) { this.selectIds = rows.map((i) => i[id]); },
    /*调用新增记录组件窗口*/
    doAdd() { this.$refs["dialog"].addDialog(); },
    /*调用编辑记录组件窗口*/
    doEdit(row) { this.$refs["dialog"].editDialog(row); },
    downloadFile(res, fileName) {
      // alert(res.type)
      let downloadElement = document.createElement("a");
      let href = window.URL.createObjectURL(new Blob([res], { type: res.type })); //创建下载的链接
      downloadElement.href = href;
      downloadElement.download = fileName; //下载后文件名
      document.body.appendChild(downloadElement);
      downloadElement.click(); //点击下载
      document.body.removeChild(downloadElement); //下载完成移除元素
      window.URL.revokeObjectURL(href); //释放blob对象
    },
  }
}