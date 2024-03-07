const settings = {
    state: {
        theme: {
            backgroundColor: "#4f4555",
            fontColor: "white"
        },
        sideBar: {
            opened: false
        }
    },
    mutations: {
        SET_THEME(state, theme){
            state.theme = theme
        },
        SET_SIDEBAR(state, sideBar){
            state.sideBar = sideBar
        }
    },
    actions: {
        setTheme({commit}, theme){
            commit("SET_THEME", theme)
        },
        setSideBar({commit}, sideBar) {
            commit("SET_SIDEBAR", sideBar)
        }
    }

}

export default settings