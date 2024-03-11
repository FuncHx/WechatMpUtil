import request from "@/utils/request"


const menu = {
    state: {
        menus: [],
    },
    mutations: {
        SET_MENUS: (state, menus) => {
            state.menus = menus
        }
    },
    actions: {
        GetMenus({commit}, data) {
            request({
                url: "/menu/getMenu",
                method: "post",
                data: data
            }).then(res => {
                commit("SET_MENUS", res.data)
            })
        }
    },
    namespaced: true,
}


export default menu