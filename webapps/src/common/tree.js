/**不分页列表页混入方法,不要增加任何与业务有关的代码,如果有相关业务代码,请将在当前列表页重写对应方法即可 */
export const treeMix = {
  data() {
    return {
      treeList: [],
      dataList: [],
      loading: false,
      form: { parentId: null, /*所属上级*/ },
    }
  },
  mounted() { this.queryTree(); },
  methods: {
    doAdd(parentId) { this.$refs["dialog"].addDialog(parentId); },
    doEdit(row) { this.$refs["dialog"].editDialog(row); },
  }
}