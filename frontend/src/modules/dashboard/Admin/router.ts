import { type RouteRecordRaw } from 'vue-router'

const moduleRoute: RouteRecordRaw = {
  path: 'admin',
  name: 'dashboardview',
  component: () => import('./Module.vue'),
  children: [
    {
      path: '',
      name: 'admin',
      component: () => import('./views/AdminView.vue')
    }
  ]
}

export default moduleRoute
