import request from '@/utils/request'
export function addMenu(form) {
    return request({
        url: "/menu/addMenu",
        method: "post",
        data: form
    })
}


export function updateMenu(form) {
    return request({
        url: "/menu/updateMenu",
        method: "post",
        data: form
    })
}

export function deleteMenu(id) {
    return request( {
        url: "/menu/deleteMenu/" + id,
        method: "get"
    } )
}