import { type RouteRecordRaw } from 'vue-router'

const moduleRoute: RouteRecordRaw = {
  path: '/moving',
  name: 'movingview',
  component: () => import('./Module.vue'),
  children: [
    {
      path: '',
      name: 'moving',
      component: () => import('./views/MovingView.vue')
    }
  ]
}

export default moduleRoute
