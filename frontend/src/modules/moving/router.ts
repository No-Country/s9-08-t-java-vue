import { type RouteRecordRaw } from 'vue-router'

const moduleRoute: RouteRecordRaw = {
  path: '/moving',
  name: 'moving',
  component: () => import('./Module.vue'),
  children: [
    {
      path: '',
      name: 'home',
      component: () => import('./views/MovingView.vue')
    }
  ]
}

export default moduleRoute
