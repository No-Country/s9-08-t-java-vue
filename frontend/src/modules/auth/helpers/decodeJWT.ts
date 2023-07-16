import type { IProfile } from '../interfaces/IProfileAuth'

export const decodeJWT = (token: string): IProfile => {
  const username: string = JSON.parse(window.atob(token.split('.')[1])).sub || ''
  return { username, token }
}
