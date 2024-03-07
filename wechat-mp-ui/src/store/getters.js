const getters = {
    roles: state => state.user.roles,
    routers: state => state.permission.addRoutes,
    sideBar: state => state.settings.sideBar
  }


export default getters