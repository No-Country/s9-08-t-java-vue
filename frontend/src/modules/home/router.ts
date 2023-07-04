import { type RouteRecordRaw } from 'vue-router'

const moduleRoute: RouteRecordRaw = {
  path: '/',
  name: 'homeview',
  component: () => import('./Module.vue'),
  children: [
    {
      path: '',
      name: 'home',
      component: () => import('./view/HomeView.vue')
    }
  ]
}

export default moduleRoute
