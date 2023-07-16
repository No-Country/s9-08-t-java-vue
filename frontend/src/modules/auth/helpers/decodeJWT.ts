import type { IProfile } from '../interfaces/IProfileAuth'

export const decodeJWT = (token: string): IProfile => {
  const decoded = JSON.parse(window.atob(token.split('.')[1])) || {}
  return { username: decoded.sub, token, roles: decoded.roles, email: decoded.email }
}
