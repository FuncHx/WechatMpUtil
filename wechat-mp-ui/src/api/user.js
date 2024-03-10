import request from '@/utils/request'

export function updateUserInfo(form) {
    return request({
        method: "post",
        data: form,
        url: "/user/update"
    })
}

export function editPassword(form) {
    return request({
        method: "post",
        data: form,
        url: "/user/editPassword"
    })
}