import Vue from 'vue'
import Vuex from 'vuex'
import user from './module/user'
import permission from './module/permission'
import getters from './getters'
import settings from "./module/settings"
import menu from "./module/menu"

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    user,
    permission,
    settings,
    menu
  },
  getters
})

export default store
