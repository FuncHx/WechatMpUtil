import request from '@/utils/request'


export function getTag() {
    return request({
      url: '/mp/tag/list',
      method: 'get'
    })
}

export function delTag(tagId) {
    return request({
        url: "/mp/tag/delete?id=" + tagId,
        method: "get"
    })
}

export function updateTag(tag) {
    return request({
        url: "/mp/tag/update",
        method: "post",
        data: tag
    })
}

export function addTag(tag){
    return request({
        url: "/mp/tag/create",
        method: "post",
        data: tag
    })
}

export function tagging(data) {
    return request({
        url: data.taggingType === "tagging" ? "/mp/tag/batchtagging" : "/mp/tag/batchuntagging",
        method: "post",
        data: {
            id: data.id,
            openidList: data.openidList
        }
    })
}