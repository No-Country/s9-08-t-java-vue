export interface IProfile {
  email: string
  username: string
  token: string
  roles: unknown[]
  id: number
}

export interface IProfileAuth {
  profile: IProfile
}
