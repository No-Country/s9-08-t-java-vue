import { AUTH_STORE_NAME } from '../constants'
import type { IProfileAuth } from '../interfaces/IProfileAuth'

export const setAuthProfile = (profile: IProfileAuth): void => {
  localStorage.setItem(AUTH_STORE_NAME, JSON.stringify(profile))
}

export const getAuthProfile = (): IProfileAuth => {
  const auth = localStorage.getItem(AUTH_STORE_NAME)
  return auth ? JSON.parse(auth) : { profile: { username: '', token: '' } }
}
