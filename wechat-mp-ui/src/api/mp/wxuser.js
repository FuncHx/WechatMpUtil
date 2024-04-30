import request from '@/utils/request'


export function getPage(page, query) {
    return request({
      url: '/mp/user/list?current='+page.currentPage+'&size='+page.pageSize,
      method: 'post',
      data: query
    })
  }

export function updateRemark(data) {
  return request({
    url: "/mp/user/remark",
    method: "post",
    data: data
  })
}