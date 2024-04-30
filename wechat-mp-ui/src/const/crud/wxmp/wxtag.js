export const tableOption = {
    dialogDrag:true,
    border: true,
    index: false,
    indexLabel: '序号',
    stripe: true,
    menuAlign: 'center',
    align: 'center',
    viewBtn: true,
    searchShow: false,
    menuWidth: 150,
    menuType:'text',
    searchMenuSpan: 6,
    defaultSort:{
      prop: 'id',
      order: 'descending'
    },
    column: [
        {
            label: 'ID',
            prop: 'id',
            editDisplay:false,
            addDisplay: false
          },
      {
        label: '昵称',
        prop: 'name',
        editDisplay:true,
      },

      {
        label: '标签人数',
        prop: 'count',
        sortable:true,
        editDisplay:false,
        addDisplay: false
      },
    ]
  }