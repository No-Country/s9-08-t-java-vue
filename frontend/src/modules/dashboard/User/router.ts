import { type RouteRecordRaw } from 'vue-router'

const moduleRoute: RouteRecordRaw = {
  path: 'user',
  name: 'user',
  component: () => import('./Module.vue'),
  children: [
    {
      path: '',
      name: 'userhome',
      component: () => import('./views/UserView.vue')
    }
  ]
}

export default moduleRoute
