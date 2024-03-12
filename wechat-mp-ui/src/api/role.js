import request from "@/utils/request"


export function getRoles(form) {
    return request({
        url: "/role/list",
        method: "post",
        data: form
    })
}