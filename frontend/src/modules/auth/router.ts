import { type RouteRecordRaw } from 'vue-router'

const moduleRoute: RouteRecordRaw = {
  path: '/auth',
  name: 'auth',
  component: () => import('./Module.vue'),
  children: [
    {
      path: 'login',
      component: () => import('./view/LoginView.vue')
    },
    {
      path: 'register',
      component: () => import('./view/RegisterView.vue')
    }
  ]
}

export default moduleRoute
