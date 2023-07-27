import { type RouteRecordRaw } from 'vue-router'
import userRouter from './User/router'
import adminRouter from './Admin/router'

const moduleRoute: RouteRecordRaw = {
  path: '/dashboard/',
  name: 'dashboard',
  component: () => import('./Module.vue'),
  children: [adminRouter, userRouter]
}

export default moduleRoute
