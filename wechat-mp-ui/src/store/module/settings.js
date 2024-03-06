const settings = {
    state: {
        theme: {
            backgroundColor: "#4f4555",
            fontColor: "white"
        }
    },
    mutations: {
        SET_THEME(state, theme){
            state.theme = theme
        },
    },
    actions: {
        setTheme({commit}, theme){
            commit("SET_THEME", theme)
        }
    }

}

export default settings