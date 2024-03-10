const getters = {
    roles: state => state.user.roles,
    routers: state => state.permission.addRoutes,
    sideBar: state => state.settings.sideBar,
    avatar: state => state.user.avatar
  }


export default getters