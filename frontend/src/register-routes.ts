import type { RouteRecordRaw } from 'vue-router'
import { router } from './router/router'

export const registerRoute = (routerModule: RouteRecordRaw | RouteRecordRaw[]) => {
  return !Array.isArray(routerModule)
    ? router.addRoute(routerModule)
    : routerModule.forEach((x) => router.addRoute(x))
}
