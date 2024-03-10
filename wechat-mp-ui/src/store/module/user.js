import { login, logout, getInfo, getRouters } from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'
import router from '@/router'

const user = {
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    roles: [],
    permissions: [],
    routers: [],
    userInfo: {}
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_PERMISSIONS: (state, permissions) => {
      state.permissions = permissions
    },
    SET_ROUTER: (state, routers) => {
      state.routers = routers
    },
    SET_USER_INFOO: (state, userInfo) => {
      state.userInfo = userInfo
    }
  },

  actions: {
    // 登录
    Login({ commit }, userInfo) {
      const username = userInfo.username.trim()
      const password = userInfo.password
      const code = userInfo.code
      const uuid = userInfo.uuid
      return new Promise((resolve, reject) => {
        login(username, password, code, uuid).then(res => {
          setToken(res.data.token)
          commit('SET_TOKEN', res.data.token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    GetInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getInfo(state.token).then(res => {
          const user = res.data
          console.log(user)
          const avatar = user.avatar == "" ? require("@/assets/images/profile.jpg") : user.avatar;
          if (user.data.roles && user.data.roles.length > 0) { // 验证返回的roles是否是一个非空数组
            commit('SET_ROLES', user.roles)
            commit('SET_PERMISSIONS', user.data.menus)
          } else {
            commit('SET_ROLES', ["普通角色"])
          }
          commit('SET_NAME', user.nikeName)
          commit('SET_AVATAR', avatar)
          commit("SET_USER_INFOO", res.data)
          resolve(res)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取菜单信息
    GetRouters({commit, state}){
      return new Promise((resolve, reject) => {
        getRouters().then(res => {
          commit("SET_ROUTER", res.data)
          resolve(res)
        }).catch(error => {
          reject(error)
        })
      })
    },
    
    // 退出系统
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          commit('SET_PERMISSIONS', [])
          removeToken()
          router.push({path: "/login"})
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    },
    
  },
  namespaced: true,
}

export default user
