import request from "@/utils/request"


export function getRoles(form) {
    return request({
        url: "/role/list",
        method: "post",
        data: form
    })
}

export function updateRoles(form) {
    return request({
        url: "/role/update",
        method: "post",
        data: form
    })
}

export function addRole(form) {
    return request({
        url: "/role/add",
        method: "post",
        data: form
    })
}

export function deleteRole(id) {
    return request({
        url: "/role/delete/"+id,
        method: "get"
    })
}