import request from '@/utils/request'
export function addMenu(form) {
    return request({
        url: "/menu/add",
        method: "post",
        data: form
    })
}


export function updateMenu(form) {
    return request({
        url: "/menu/update",
        method: "post",
        data: form
    })
}

export function deleteMenu(id) {
    return request( {
        url: "/menu/delete/" + id,
        method: "get"
    } )
}