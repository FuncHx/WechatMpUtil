import request from '@/utils/request'


// 登录方法
export function login(username, password, code, uuid) {
    const data = {
      username,
      password,
      code,
      uuid
    }
    return request({
      url: '/user/login',
      method: 'post',
      data: data
    })
  }
  
  // 获取用户详细信息
  export function getInfo() {
    return request({
      url: '/user/userInfo',
      method: 'get'
    })
  }
  
  // 退出方法
  export function logout() {
    return request({
      url: '/user/logout',
      method: 'get'
    })
  }
  
  // 获取验证码
  export function getCodeImg() {
    return request.get("/captchaImage")
  }

  // 获取菜单信息
  export function getRouters(){
    return request.get("/user/getRouters")
  }