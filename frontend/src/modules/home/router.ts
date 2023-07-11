import { type RouteRecordRaw } from 'vue-router'

const moduleRoute: RouteRecordRaw = {
  path: '/',
  name: 'home',
  component: () => import('./Module.vue'),
  children: [
    {
      path: '',
      name: 'home',
      component: () => import('./views/HomeView.vue')
    }
  ]
}

export default moduleRoute
