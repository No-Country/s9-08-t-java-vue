import { type RouteRecordRaw } from 'vue-router'

const moduleRoute: RouteRecordRaw = {
  path: '/auth',
  name: 'auth',
  redirect: '/auth/login',
  component: () => import('./Module.vue'),
  children: [
    {
      path: 'login',
      component: () => import('./views/LoginView.vue')
    },
    {
      path: 'register',
      component: () => import('./views/RegisterView.vue')
    }
  ]
}

export default moduleRoute
